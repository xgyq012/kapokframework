package com.nateiot.cis.service.impl;

import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.base.repository.GxwlSysUserDao;
import com.nateiot.cis.domain.CisEsIntegalDetail;
import com.nateiot.cis.repository.CisEsIntegalDetailDao;
import com.nateiot.cis.service.CisEsIntegalDetailService;
import com.nateiot.cis.service.CisEsIntegalService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 考核督办 -- 积分明细记录
 * 
 *  @author Guohw
 */
@Service(value = "cisEsIntegalDetailService")
@Transactional(readOnly = true)
public class CisEsIntegalDetailServiceImpl extends 
		BaseServiceImpl<CisEsIntegalDetailDao, CisEsIntegalDetail, Integer> implements
		CisEsIntegalDetailService{
	
	@Autowired
	private CisEsIntegalDetailDao cisEsIntegalDetailDao;
	
	@Autowired
	private CisEsIntegalService cisEsIntegalService;
	
	@Autowired
	private GxwlSysUserDao gxwlSysUserDao;
	
	@Autowired
	private CisEsIntegalDetailServiceImpl(CisEsIntegalDetailDao cisEsIntegalDetailDao){
		super(cisEsIntegalDetailDao);
	}
	
	/**
	 * 查询 
	 */
	@Override
	public Map<String, Object> searchDetail(String timeGte, 
			String timeLte, Map<String, SearchFilter> conditions, Pageable pageable){
		resetResultMap();
		try{
			Page<Map<String, Object>> page = cisEsIntegalDetailDao.searchDetail(
					timeGte, timeLte,conditions, pageable);
			
			resultMap.put(RESULT_ROWS, page.getContent());
			resultMap.put(RESULT_TOTAL, page.getTotalElements());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		return resultMap;
	}
	
	/**
	 *  积分明细记录数据(接口)
	 *  
	 *  @param userId(用户ID), detailType(积分类型), detailTime(计分时间), 
	 *  voucherId(单据ID), voucherType(单据类型), remark(备注)
	 *  
	 *  @return
	 */ 
	@Override
	public Map<String, Object> scoreDetail(Integer userId, String detailType,
			Date detailTime, Integer voucherId, String voucherType, String remark){
		if("loginScore".equals(detailType)){
			List<Map<String, Object>> entity = cisEsIntegalDetailDao.searchLoginTimes(userId);
			  if(entity.isEmpty()){
				  score(userId, detailType, detailTime, voucherId, voucherType, remark);
			  }
			  
		}else{
			score(userId, detailType, detailTime, voucherId,voucherType,remark);
		}
		return null;
	}
	
	
	private void score(Integer userId, String detailType,Date detailTime, 
				Integer voucherId, String voucherType, String remark){
		if(detailType != null && detailType.length() != 0){
			resetResultMap();
			try{
					GxwlSysUser userInfo = gxwlSysUserDao.findOne(userId);
					CisEsIntegalDetail detailInfo = new CisEsIntegalDetail();
					detailInfo.setUserId(userInfo.getUserId());
					detailInfo.setUserName(userInfo.getUserName());
					detailInfo.setRealName(userInfo.getRealname());
					detailInfo.setDetailTime(detailTime);
					detailInfo.setDetailType(detailType);
					detailInfo.setVoucherId(voucherId);
					detailInfo.setVoucherType(voucherType);
					detailInfo.setRemark(remark);
					detailInfo.setCreateBy(userInfo);
					detailInfo.setCreateTime(new Date());
					
					Integer score = cisEsIntegalService.findScore(detailType);
					if(score != null){
						detailInfo.setScore(score);
					}
					
					cisEsIntegalDetailDao.save(detailInfo);
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
}
