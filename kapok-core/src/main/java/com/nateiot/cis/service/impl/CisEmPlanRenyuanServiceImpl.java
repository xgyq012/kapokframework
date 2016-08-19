package com.nateiot.cis.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisEmPlanRenyuan;
import com.nateiot.cis.domain.CisEmYingjiPlan;
import com.nateiot.cis.repository.CisEmPlanRenyuanDao;
import com.nateiot.cis.repository.CisEmYingjiPlanDao;
import com.nateiot.cis.service.CisEmPlanRenyuanService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("CisEmPlanRenyuanService")
public class CisEmPlanRenyuanServiceImpl extends BaseServiceImpl<CisEmPlanRenyuanDao, CisEmPlanRenyuan, Integer> implements CisEmPlanRenyuanService{

	@Autowired
	private CisEmPlanRenyuanDao cisEmPlanRenyuanDao;
	
	@Autowired 
	private CisEmYingjiPlanDao cisEmYingjiPlanDao;
	
	@Autowired
	public CisEmPlanRenyuanServiceImpl(CisEmPlanRenyuanDao d) {
		super(d);
	}

	@Override
	@Transactional
	public Map<String, Object> save(String planRenyuanJson) {
		try {
			resetResultMap();
			ObjectMapper objectMapper = new ObjectMapper();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			objectMapper.setDateFormat(dateFormat);
			List<CisEmPlanRenyuan> planRenyuanList = 
					objectMapper.readValue(planRenyuanJson, new TypeReference<List<CisEmPlanRenyuan>>() {});
			if(! planRenyuanList.isEmpty()){
				
				//从过去的应急人员中删除不要的
				CisEmYingjiPlan plan = cisEmYingjiPlanDao.findOne(planRenyuanList.get(0).getYingjiPlanId());
				
				//存放要删除的应急人员的列表
				List<CisEmPlanRenyuan> delRenyuanList = new ArrayList<CisEmPlanRenyuan>();
				for(CisEmPlanRenyuan entity : plan.getPlanRenyuanList()){
					boolean yes = false;
					for(CisEmPlanRenyuan entity2 : planRenyuanList){
						
						//判断是否要保留该应急人员
						if(entity2.getPlanRenyuanId() != null 
								&& entity2.getPlanRenyuanId() == entity.getPlanRenyuanId()){
							yes = true;
							break;
						}
					}
					
					//当前用户已经不存在与新的应急人员列表中时，删除之
					if(yes == false){
						delRenyuanList.add(entity);
					}
				}
				
				cisEmPlanRenyuanDao.deleteInBatch(delRenyuanList);
				//保存新添加的应急人员信息
				for(CisEmPlanRenyuan entity : planRenyuanList){
					if(entity.getPlanRenyuanId() == null){
						super.doSave(entity);
					}else{
						CisEmPlanRenyuan entity2 = cisEmPlanRenyuanDao.findOne(entity.getPlanRenyuanId());
						entity2.setRemark(entity.getRemark());
						cisEmPlanRenyuanDao.save(entity2);
					}
				}
			}
			setResultStatus(0, "保存成功");
			resultMap.put(RESULT_ROW, null);
			return resultMap;
		} catch (IOException e) {
			e.printStackTrace();
			setResultStatus(-1, "保存时系统出错: " + e.getMessage());
			return resultMap;
		}
	}

	
	
}
