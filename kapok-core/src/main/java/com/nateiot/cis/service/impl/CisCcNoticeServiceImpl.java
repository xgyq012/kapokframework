package com.nateiot.cis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.domain.GxwlSysOrg;
import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.base.repository.GxwlSysOrgDao;
import com.nateiot.base.repository.GxwlSysUserDao;
import com.nateiot.cis.domain.CisCcMyNotice;
import com.nateiot.cis.domain.CisCcNotice;
import com.nateiot.cis.domain.CisCcTargetUser;
import com.nateiot.cis.repository.CisCcMyNoticeDao;
import com.nateiot.cis.repository.CisCcNoticeDao;
import com.nateiot.cis.repository.CisCcTargetUserDao;
import com.nateiot.cis.service.CisCcNoticeService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 消息公告或短信消息
 * @author xiewenhua
 *
 */
@Service(value = "CisCcNoticeService")
@Transactional
public class CisCcNoticeServiceImpl extends 
    BaseServiceImpl<CisCcNoticeDao, CisCcNotice, Integer> implements CisCcNoticeService {
	
	@PersistenceContext
	protected EntityManager entityManager;
	@Autowired
	private CisCcNoticeDao cisCcNoticeDao;
	@Autowired
	private GxwlSysOrgDao sysOrgDao;
	@Autowired
	private CisCcMyNoticeDao cisCcMyNoticeDao;
	
	@Autowired
	private CisCcTargetUserDao cisCcTargetUserDao;
	
	@Autowired
	private GxwlSysUserDao userDao;
	
	@Autowired
	public CisCcNoticeServiceImpl(CisCcNoticeDao d) {
		super(d);
	}

	@Override
	@Transactional
	public Map<String, Object> doSave(CisCcNotice entity) {
		try{
			resetResultMap();
			if(1 == entity.getSendStatus()){
				entity.setSendTime(new Date());
			}
			entity.setDelSign("N");
			save(entity);
			
			//保存接受公告信息的目标对象
			sendNotice(entity);
			resultMap.put(RESULT_ROW, entity);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "保存成功");
			
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_ROW, "");
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "保存出错！");
			
		}
		return resultMap;
	}
	
	private void save(CisCcNotice notice){
		List<CisCcTargetUser> targetUserList = notice.getTargetList();
		if(notice.getNoticeId() == null){
			cisCcNoticeDao.save(notice);
			if(targetUserList != null){
				for(CisCcTargetUser tgUser: targetUserList){
					tgUser.setNoticeId(notice.getNoticeId());
				}	
			}
		}else{
			if(targetUserList != null){
				CisCcNotice tempNotice = cisCcNoticeDao.findOne(notice.getNoticeId());
				
				//存放要删除的接受消息公告的用户
				List<CisCcTargetUser> delTargetUserList = new ArrayList<CisCcTargetUser>();
				if(tempNotice.getTargetList() != null){
					for(CisCcTargetUser entity : tempNotice.getTargetList()){
						boolean yes = false;
						for(CisCcTargetUser entity2 : targetUserList){
							
							//判断是否要保留该应急人员
							if(entity2.getTargetId() != null 
									&& entity2.getTargetId().intValue() == entity.getTargetId().intValue()){
								yes = true;
								break;
							}
						}
						
						//当前用户已经不存在与新的应急人员列表中时，删除之
						if(yes == false){
							delTargetUserList.add(entity);
						}
					}
				}
				cisCcTargetUserDao.deleteInBatch(delTargetUserList);
				
				//保存新添加的应急人员信息
				for(CisCcTargetUser entity : targetUserList){
					if(entity.getTargetId() == null){
						entity.setNoticeId(entity.getNoticeId());
					}
				}
			}
		}
		cisCcNoticeDao.save(notice);
	}
	
	
	
	/**
	 * 发送公告
	 * @param entity
	 */
	private void sendNotice(CisCcNotice entity){
		if(entity.getSendStatus() == 1){
			List<Integer> userIds = getTargetUser(entity.getNoticeId());
			if(! userIds.isEmpty()){
				newNoticesAndSave(entity, userIds);
			}
		}
	}
	
	/**
	 * 返回接受指定公告的用户
	 * @param noticeId
	 * @return
	 */
	private List<Integer> getTargetUser(Integer noticeId){
		CisCcNotice notice = cisCcNoticeDao.findOne(noticeId);
		List<CisCcTargetUser> tgList = notice.getTargetList();
		boolean sendToAll = false;  //发送给所有用户
		
		//需要发送到的机构的id
		List<Integer> selectJigou = new ArrayList<Integer>();
		
		//子机构id列表
		List<Integer> ziJigouIds = new ArrayList<Integer>();
		
		//指定收到消息的用户的id
		List<Integer> userIds = new ArrayList<Integer>();
		
		for(CisCcTargetUser user : tgList){
			if("个人".equals(user.getTargetType())){
				userIds.add(user.getTargetUserId());
			}else if("网格".equals(user.getTargetType())){
				selectJigou.add(user.getTargetMeshId());
			}else if("所有".equals(user.getTargetType())){
				sendToAll = true;
				break;
			}
		}
		
		//接受消息公告的用户不是所有人时，需要查找接受人
		if(! sendToAll){
			if(! selectJigou.isEmpty()){
				
				//查询数据库，找出所有需要收到消息的机构的id，及指定机构及其子机构
				for(int i = 0; i < selectJigou.size(); i++){
					List<GxwlSysOrg> orgList = sysOrgDao.findByParentOrgIdOrderByIdAsc(selectJigou.get(i));
					for(int j = 0; j < orgList.size(); j++){
						ziJigouIds.add(orgList.get(j).getId());
					}
				}
				selectJigou.addAll(ziJigouIds);
				
				//查询需要接受消息的机构下的人员
				for(int i = 0; i < selectJigou.size(); i ++){
				    List<Map<String, Object>> tempUserList = sysOrgDao.findUserByOrgId(selectJigou.get(i));
				    for(int j = 0; j < tempUserList.size(); j ++){
				    	Map<String, Object> map = tempUserList.get(j);
				    	userIds.add(Integer.parseInt(map.get("userId").toString()));
				    }
				}
			}
		}else{
			
			//XXX 查出所有用户？？ 不妥吧
			List<GxwlSysUser> allUser = userDao.findAll();
			for(GxwlSysUser user : allUser){
				userIds.add(user.getUserId());
			}
		}
		return userIds;
	}
	
	
	private void newNoticesAndSave(CisCcNotice entity, List<Integer> userIds){
		for(int i = 0; i < userIds.size(); i ++){
			CisCcMyNotice myNotice = new CisCcMyNotice();
			myNotice.setCreaterId(0);
			myNotice.setCreateTime(new Date());
			myNotice.setUserId(userIds.get(i));
			myNotice.setNoticeId(entity.getNoticeId());
			myNotice.setReadStatus(0);
			entityManager.persist(myNotice);
			if(i % 30 == 0){
				entityManager.flush();
				entityManager.clear();
			}
		}
	}



	@Override
	@Transactional
	public Map<String, Object> softDel(List<Integer> ids) {
		resetResultMap();
		try{
			for(Integer id : ids){
				CisCcNotice notice = cisCcNoticeDao.findOne(id);
				notice.setDelSign("Y");
				cisCcNoticeDao.save(notice);
			}
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "删除成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}

		return resultMap;
	}

	private List<Integer> getIds(String ids){
		List<Integer> list = new ArrayList<Integer>();
		if("".equals(ids.trim())){
			return list;
		}
		String strs[] = ids.split(",");
		for(int i = 0; i < strs.length; i++){
			list.add(Integer.parseInt(strs[i]));
		}
		return list;
	}
	
}
