package com.nateiot.cis.service.impl;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisSwServiceTeam;
import com.nateiot.cis.domain.CisSwTeamMember;
import com.nateiot.cis.repository.CisSwServiceTeamDao;
import com.nateiot.cis.repository.CisSwTeamMemberDao;
import com.nateiot.cis.service.CisSwServiceTeamService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 服务办事 -- 服务团队
 * 
 *  @author Guohw
 */
@Service(value = "cisSwServiceTeamService")
@Transactional(readOnly = true)
public class CisSwServiceTeamServiceImpl 
		extends BaseServiceImpl<CisSwServiceTeamDao, CisSwServiceTeam, Integer>
		implements CisSwServiceTeamService{

	@Autowired
	private CisSwServiceTeamDao cisSwServiceTeamDao;
	
	@Autowired
	private CisSwTeamMemberDao cisSwTeamMemberDao;
	
	@Autowired
	public CisSwServiceTeamServiceImpl(CisSwServiceTeamDao d) {
		super(d);
	}
	
	/**
	 * 详细 
	 */
//	@Override
//	public Map<String, Object> teamSelect(Integer teamId) {
//		resetResultMap();
//		try{
//			CisSwServiceTeam entity = cisSwServiceTeamDao.findOne(teamId);
//			Map<String, Object> row = new HashMap<String, Object>();
//			row.put("team", cisSwServiceTeamDao.save(entity));
//			row.put("teamMember", cisSwTeamMemberDao.findByTeamId(teamId));
//			
//			resultMap.put(RESULT_ROW, row);
//			resultMap.put(RESULT_CODE, 0);
//			resultMap.put(RESULT_MSG, "保存成功");
//		}catch(Exception e){
//			e.printStackTrace();
//			resultMap.put(RESULT_CODE, -1);
//			resultMap.put(RESULT_MSG, "保存出错");
//		}
//		return resultMap;
//	}

	
	/**
	 * 保存 
	 */
	@Override
	@Transactional
	public Map<String, Object> teamSave(CisSwServiceTeam cisSwServiceTeam, String root) {
		resetResultMap();
		try{
			if(StringUtils.isEmpty(cisSwServiceTeam.getDelSign())){
				cisSwServiceTeam.setDelSign("N");
			}
			
			Integer teamId = cisSwServiceTeam.getTeamId();
			
			if(teamId == null){
				CisSwServiceTeam entity = cisSwServiceTeamDao.save(cisSwServiceTeam);
				List<CisSwTeamMember> teamMember = entity.getCisSwTeamMember();
				if(teamMember != null){
					for(CisSwTeamMember member : teamMember){
//						member.setCisSwServiceTeam(model);
						member.setTeamId(entity.getTeamId());
					}
					
				}
			}else{
				CisSwServiceTeam d = cisSwServiceTeamDao.findOne(teamId);
				
				List<CisSwTeamMember> olds = d.getCisSwTeamMember();
				List<CisSwTeamMember> news = cisSwServiceTeam.getCisSwTeamMember();
				
				if(news == null){
					cisSwTeamMemberDao.deleteInBatch(olds);
				}else{
					for(CisSwTeamMember member : olds){
						if(!news.contains(member)){
							cisSwTeamMemberDao.delete(member);
						}
						
					}
					for(CisSwTeamMember tm : news){
						tm.setTeamId(cisSwServiceTeam.getTeamId());
					}
				}
			}
			
			resultMap.put(RESULT_ROW, cisSwServiceTeamDao.save(cisSwServiceTeam));
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "保存出错");
		}
		return resultMap;
	}
	
}
