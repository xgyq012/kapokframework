package com.nateiot.cis.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.base.repository.GxwlSysDocDao;
import com.nateiot.base.service.GxwlSysDocService;
import com.nateiot.cis.domain.CisEmYjsjDoc;
import com.nateiot.cis.repository.CisEmYjsjDocDao;
import com.nateiot.cis.service.CisEmYjsjDocService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 应急事件附件
 * @author xiewenhua
 *
 */
@Service("CisEmYjsjDocService")
@Transactional
public class CisEmYjsjDocServiceImpl extends BaseServiceImpl<CisEmYjsjDocDao, CisEmYjsjDoc, Integer> implements CisEmYjsjDocService{

	@Autowired
	private CisEmYjsjDocDao cisEmYjsjDocDao;
	
	@Autowired
	private GxwlSysDocService docService;
	
	@Autowired
	private GxwlSysDocDao docDao;
	
	@Autowired
	public CisEmYjsjDocServiceImpl(CisEmYjsjDocDao d) {
		super(d);
	}

	@Override
	@Transactional
	public Map<String, Object> doDelete(Iterable<CisEmYjsjDoc> entities) {
		try{
			resetResultMap();
			for(CisEmYjsjDoc yjsjDoc : entities){
				docService.doDelete(yjsjDoc.getDocId());
				cisEmYjsjDocDao.delete(yjsjDoc);
			}
			setResultStatus("0", "删除成功!");
		}catch(Exception e){
			e.printStackTrace();
			setResultStatus(-1, "删除出错");
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> doSave(CisEmYjsjDoc entity) {
		if(entity.getDocId() != null){
			GxwlSysDoc doc = docDao.findOne(entity.getDocId());
			doc.setIsTemp("N");
		}
		return super.doSave(entity);
	}
	
	
	
	
}
