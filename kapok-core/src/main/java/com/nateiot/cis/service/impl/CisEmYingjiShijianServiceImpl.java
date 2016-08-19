package com.nateiot.cis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.common.SessionUtil;
import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.base.repository.GxwlSysDocDao;
import com.nateiot.base.repository.GxwlSysOrgDao;
import com.nateiot.base.repository.GxwlSysOrgUserDao;
import com.nateiot.base.service.GxwlSysOrgService;
import com.nateiot.cis.domain.CisEmYingjiShijian;
import com.nateiot.cis.domain.CisEmYjsjDoc;
import com.nateiot.cis.repository.CisBmCommunityMeshDao;
import com.nateiot.cis.repository.CisBmCommunityMeshUserDao;
import com.nateiot.cis.repository.CisEmYingjiShijianDao;
import com.nateiot.cis.repository.CisEmYjsjDocDao;
import com.nateiot.cis.service.CisEmYingjiShijianService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 应急事件
 * @author xiewenhua
 *
 */
@Service(value = "CisEmYingjiShijianService")
@Transactional
public class CisEmYingjiShijianServiceImpl extends
    BaseServiceImpl<CisEmYingjiShijianDao, CisEmYingjiShijian, Integer> implements 
    CisEmYingjiShijianService {
	//事件处理进度
//	private Integer SHIJIAN_STATUS_CAOGOU = 0;  //草稿
	private Integer SHIJIAN_STATUS_TIJIAO = 1;  //已提交
	private Integer SHIJIAN_STATUS_HECHA = 2;   //已核查
//	private Integer SHIJIAN_STATUS_SHENCHA = 3; //已审查
//	private Integer SHIJIAN_STATUS_GENJING = 4; //正在跟进
//	private Integer SHIJIAN_STATUS_OK = 9;      //已处理

	@Autowired
	private CisEmYingjiShijianDao cisEmYingjiShijianDao;
	
	@Autowired
	private CisEmYjsjDocDao yjsjDocDao;
	
	@Autowired
	private GxwlSysDocDao docDao;
	
	@Autowired
	public CisEmYingjiShijianServiceImpl(CisEmYingjiShijianDao d) {
		super(d);
	}
	@Autowired
	private GxwlSysOrgService orgService;
	
	@Autowired
	private GxwlSysOrgDao orgDao;
	
	@Autowired
	private GxwlSysOrgUserDao orgUserDao;
	
	@Autowired
	private CisBmCommunityMeshDao meshDao;
	
	@Autowired
	private CisBmCommunityMeshUserDao meshUserDao;
	
	@Override
	@Transactional
	public Map<String, Object> doSave(CisEmYingjiShijian entity) {
		try{
			resetResultMap();
			List<CisEmYjsjDoc> newDocs = entity.getCisEmYjsjDocs();
			if(entity.getYingjiShijianId() != null){
				CisEmYingjiShijian yjsj = cisEmYingjiShijianDao.findOne(entity.getYingjiShijianId());
				List<CisEmYjsjDoc> docs = yjsj.getCisEmYjsjDocs();
				List<CisEmYjsjDoc> delDocs = new ArrayList<CisEmYjsjDoc>();
				
				//找出被刪除的附件并保存到delDocs集合中
				if(docs != null && ! docs.isEmpty()){
					for(CisEmYjsjDoc doc : docs){
						boolean isDelDoc = true;
						if(newDocs != null){
							for(CisEmYjsjDoc ndoc : newDocs){
								if(ndoc.getYjsjDocId() != null 
										&& ndoc.getYjsjDocId().intValue() == doc.getYjsjDocId().intValue()){
									isDelDoc = false;
									break;
								}
							}
						}

						if(isDelDoc){
							GxwlSysDoc sysDoc = docDao.findOne(doc.getDocId());
							sysDoc.setIsTemp("Y");
							docDao.save(sysDoc);
							delDocs.add(doc);
						}
					}
				}
				if(! delDocs.isEmpty()){
					yjsjDocDao.deleteInBatch(delDocs);
				}
			}else{
				cisEmYingjiShijianDao.save(entity);
			}
			
			if(newDocs != null && ! newDocs.isEmpty()){
				for(CisEmYjsjDoc ndoc : newDocs){
					if(ndoc.getYingjiShijianId() == null){
						ndoc.setYingjiShijianId(entity.getYingjiShijianId());
						GxwlSysDoc doc = docDao.findOne(ndoc.getDocId());
						doc.setIsTemp("N");
						docDao.save(doc);
					}
				}
			}
			
			resultMap.put(RESULT_ROW, cisEmYingjiShijianDao.save(entity));
			resultMap.put(RESULT_CODE, 0);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_ROW, "");
			resultMap.put(RESULT_CODE, -1);
		}
		return resultMap;
	}
	
	@Override
	public boolean isHechaOrShenchaRen(Integer yingjiShijianId) {
		CisEmYingjiShijian entity = cisEmYingjiShijianDao.findOne(yingjiShijianId);
		Integer userId = SessionUtil.getCurrentUser().getUserId();//当前用户登入用户的id
		
		//当前记录已经提交当尚未进行核查的，判断当前用户是不是核查人
		if(entity.getShijianStatus() == SHIJIAN_STATUS_TIJIAO
				&& entity.getHechaTime() == null){
			List<Map<String, Object>> list = meshUserDao.findTonshi(entity.getLururenId());
			for(Map<String, Object> map : list){
				if(map.get("userId") != null 
						&& userId == Integer.parseInt(map.get("userId").toString())){
					return true;
				}
			}
			return false;
		}
		
		//如果当前应急事件记录已经核查并已经上报且尚未尚未审查的，判断当前用户是否是审查人
		else if(entity.getShijianStatus() == SHIJIAN_STATUS_HECHA 
				&& null == entity.getShenchaTime()
				&& "Y".equals(entity.getShifouShangbao())){
			List<Map<String, Object>> list = meshUserDao.findLindao(entity.getLururenId());
			for(Map<String, Object> map : list){
				if(map.get("userId") != null 
						&& userId == Integer.parseInt(map.get("userId").toString())){
					return true;
				}
			}
			return false;
		}
		
		return false;
	}	
}