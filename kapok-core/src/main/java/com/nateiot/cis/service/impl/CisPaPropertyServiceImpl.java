package com.nateiot.cis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.base.repository.GxwlSysDocDao;
import com.nateiot.cis.domain.CisEmYingjiRenyuan;
import com.nateiot.cis.domain.CisPaProperty;
import com.nateiot.cis.domain.CisPaPropertyDetails;
import com.nateiot.cis.repository.CisPaPropertyDao;
import com.nateiot.cis.repository.CisPaPropertyDetailsDao;
import com.nateiot.cis.service.CisPaPropertyService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("cisPaPropertyService")
@Transactional
public class CisPaPropertyServiceImpl extends BaseServiceImpl<CisPaPropertyDao, CisPaProperty, Integer> implements CisPaPropertyService{

	@Autowired
	private CisPaPropertyDao cisPaPropertyDao;
	
	@Autowired
	private CisPaPropertyDetailsDao detailsDao;
	
	@Autowired
	private GxwlSysDocDao docDao;
	
	@Autowired
	public CisPaPropertyServiceImpl(CisPaPropertyDao d) {
		super(d);
	}

	@Override
	@Transactional
	public Map<String, Object> doSave(CisPaProperty entity) {
		try{
			resetResultMap();
			entity.setDelSign("N");
			Integer id = entity.getPropertyId();
			List<GxwlSysDoc> docs = new ArrayList<GxwlSysDoc>();
			
			//新增一条记录
			if(id == null){
				cisPaPropertyDao.save(entity);
				List<CisPaPropertyDetails> list = entity.getProDetList();
				if(list != null){
					for(CisPaPropertyDetails details : list){
						
						//将详细信息中的财产图片设为非临时文件
						if(details.getImageId() != null){
							GxwlSysDoc doc = docDao.findOne(details.getImageId());
							doc.setIsTemp("N");
							docDao.save(doc);
						}
						details.setPropertyId(entity.getPropertyId());
					}	
				}
			}else{
				CisPaProperty pro = cisPaPropertyDao.findOne(id);
				List<CisPaPropertyDetails> list = pro.getProDetList();
				List<CisPaPropertyDetails> newDet = entity.getProDetList();
				
				//没有详细记录时
				if(newDet == null){
					
					//找出详细信息中的待删除的图片
					if(list != null && ! list.isEmpty()){
						for(CisPaPropertyDetails details : list){
							if(details.getImageId() != null){
								docs.add(docDao.findOne(details.getImageId()));
							}
						}	
					}
					
					//删除所有详细记录
					detailsDao.deleteInBatch(list);
				}
				
				else{
					
					//存放被删除的明细
					List<CisPaPropertyDetails> removeEntitys = new ArrayList<CisPaPropertyDetails>();
					
					//找出被删除的明细 以及被更换的财产图片
					boolean b = false;  //是否保留记录
					for(CisPaPropertyDetails details : list){
						for(CisPaPropertyDetails details2 : newDet){
							
							//如果详细记录需要保留
							if(details2.getPropertyDetailsId() != null 
									&& details2.getPropertyDetailsId() == details.getPropertyDetailsId()){
								
								//更换图片 将被更换图片加入到待删除集合中
								if(details2.getImageId() != null && details.getImageId() != null 
										&& details2.getImageId().intValue() != details.getImageId().intValue()){
									docs.add(docDao.findOne(details.getImageId()));
								}
								b = true;
								break;
							}
						}
						
						//如果记录需要删除
						if(b == false){
							if(details.getImageId() != null){
								docs.add(docDao.findOne(details.getImageId()));
							}
							removeEntitys.add(details);
						}
						b = false;
					}
					detailsDao.deleteInBatch(removeEntitys);
					
					//保存详细财产信息
					for(CisPaPropertyDetails details : newDet){
						if(details.getPropertyDetailsId() == null){
							details.setPropertyId(entity.getPropertyId());
						}
						
						//将财产图片设为非临时状态
						if(details.getImageId() != null){
							GxwlSysDoc doc = docDao.findOne(details.getImageId());
							if("Y".equals(doc.getIsTemp())){
								doc.setIsTemp("N");
								docDao.save(doc);
							}
						}
					}
				}
			}
			
			//删除无效的财产图片
			docDao.deleteInBatch(docs);
			
			resultMap.put(RESULT_ROW, cisPaPropertyDao.save(entity));
			setResultStatus(0, "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			setResultStatus(-1, "保存时系统出错: " + e.getMessage());
		}
		
		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, Object> softDelList(List<Integer> ids) {
		try{
			resetResultMap();
			for(Integer id : ids){
				CisPaProperty entity = cisPaPropertyDao.findOne(id);
				entity.setDelSign("Y");
				cisPaPropertyDao.save(entity);
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		return resultMap;
	}
	
}
