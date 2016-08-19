package com.nateiot.cis.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.common.SessionUtil;
import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.base.repository.GxwlSysUserDao;
import com.nateiot.cis.domain.CisSwConditionsDiary;
import com.nateiot.cis.repository.CisSwConditionsDiaryDao;
import com.nateiot.cis.service.CisEsIntegalDetailService;
import com.nateiot.cis.service.CisSwConditionsDiaryService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 服务办事 -- 民情日记
 * 
 *  @author Guohw
 */
@Service(value = "CisSwConditionsDiary")
@Transactional(readOnly = true)
public class CisSwConditionsDiaryServiceImpl 
		extends BaseServiceImpl<CisSwConditionsDiaryDao, CisSwConditionsDiary, Integer>
		implements CisSwConditionsDiaryService{

	@Autowired
	private CisSwConditionsDiaryDao cisSwConditionsDiaryDao;
	
	@Autowired
	private CisEsIntegalDetailService cisEsIntegalDetailService;
	
	@Autowired
	private GxwlSysUserDao gxwlSysUserDao;
	
	@Autowired
	public CisSwConditionsDiaryServiceImpl(CisSwConditionsDiaryDao d) {
		super(d);
	}
	
	/**
	 * 保存 
	 */
	@Override
	@Transactional
	public Map<String, Object> conditionsSave(CisSwConditionsDiary cisSwConditionsDiary){
		resetResultMap();
		try{
			Integer diaryId = cisSwConditionsDiary.getDiaryId();
			Integer userId = SessionUtil.getCurrentUser().getUserId();
			
			//新增
			if(diaryId == null){
				CisSwConditionsDiary bean = cisSwConditionsDiaryDao.save(cisSwConditionsDiary);
				bean.setDiaryStatus("draft");
//				bean.setDelSign("N");
//				bean.setSubmitTime(new Date());
				resultMap.put(RESULT_ROW, cisSwConditionsDiaryDao.save(bean));
				resultMap.put(RESULT_CODE, 0);
				resultMap.put(RESULT_MSG, "保存成功");
				return resultMap;
				
		    //修改
			}else{
				CisSwConditionsDiary bean = cisSwConditionsDiaryDao.findOne(diaryId);
				if("submit".equals(bean.getDiaryStatus())){
					bean.setDiaryStatus("estimate");
					bean.setEstimateTime(new Date());
					bean.setEstimateContent(cisSwConditionsDiary.getEstimateContent());
					bean.setEstimateLevel(cisSwConditionsDiary.getEstimateLevel());
					bean.setEstimatePerson(cisSwConditionsDiary.getEstimatePerson());
					cisEsIntegalDetailService.scoreDetail(userId
							, cisSwConditionsDiary.getEstimateLevel(), cisSwConditionsDiary.getCreateTime()
							, null, null, null);
					resultMap.put(RESULT_ROW, cisSwConditionsDiaryDao.save(bean));
					resultMap.put(RESULT_CODE, 0);
					resultMap.put(RESULT_MSG, "保存成功");
					return resultMap;
				}
			}
			
			resultMap.put(RESULT_ROW, cisSwConditionsDiaryDao.save(cisSwConditionsDiary));
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "保存出错");
		}
		return resultMap;
	}

	/**
	 * 判断状态 
	 */
	@Override
	public Map<String, Object> gain(Integer diaryId) {
		resetResultMap();
		try{
			
			resultMap.put(RESULT_ROW, null);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "保存成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "保存出错");
		}
		return resultMap;
	}

	/**
	 * 提交 
	 */
	@Override
	@Transactional
	public Map<String, Object> submit(Integer diaryId) {
		resetResultMap();
		try{
			CisSwConditionsDiary entity = cisSwConditionsDiaryDao.findOne(diaryId);
			if(entity.getCreateBy() == null){
				Integer createBy = SessionUtil.getCurrentUser().getUserId();
				GxwlSysUser user = gxwlSysUserDao.findOne(createBy);
				entity.setCreateBy(user);
			}
			entity.setDiaryStatus("submit");
			entity.setSubmitTime(new Date());
			resultMap.put(RESULT_ROW, cisSwConditionsDiaryDao.save(entity));
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
