package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.base.repository.GxwlSysDocDao;
import com.nateiot.base.service.GxwlSysDocService;
import com.nateiot.cis.domain.CisEmYjsjClmx;
import com.nateiot.cis.repository.CisEmYjsjClmxDao;
import com.nateiot.cis.service.CisEmYjsjClmxService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service(value = "CisEmYjsjClmxService")
@Transactional
public class CisEmYjsjClmxServiceImpl extends BaseServiceImpl<CisEmYjsjClmxDao, CisEmYjsjClmx, Integer> implements CisEmYjsjClmxService{

	@Autowired
	private CisEmYjsjClmxDao cisEmYjsjClmxDao;
	@Autowired
	private GxwlSysDocService docService;
	@Autowired
	private GxwlSysDocDao docDao;
	@Autowired
	public CisEmYjsjClmxServiceImpl(CisEmYjsjClmxDao d) {
		super(d);
	}

	@Override
	@Transactional
	public Map<String, Object> doDelete(Iterable<CisEmYjsjClmx> entities) {
		try{
			resetResultMap();
			for(CisEmYjsjClmx clmx : entities){
				if(clmx.getFileId() != null){
					docService.deleteDoc(clmx.getFileId());
				}
				cisEmYjsjClmxDao.delete(clmx);
			}
			setResultStatus("0", "删除成功!");
		}catch(Exception e){
			e.printStackTrace();
			setResultStatus(-1, "删除出错");
		}
		return resultMap;
	}
	
/*	private void delDocs(String ids){
		String strs[] = ids.split(",");
		for(int i = 0; i < strs.length; i ++){
			docService.deleteDoc(Integer.parseInt(strs[i]));
		}
	}*/

	@Override
	public Map<String, Object> doSave(CisEmYjsjClmx entity) {
		if(entity.getFileId() != null){
			GxwlSysDoc doc = docDao.findOne(entity.getFileId());
			doc.setIsTemp("N");	
		}
		return super.doSave(entity);
	}
	
	
}
