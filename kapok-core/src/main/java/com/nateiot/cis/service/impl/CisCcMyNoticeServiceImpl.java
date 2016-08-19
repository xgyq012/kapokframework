package com.nateiot.cis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisCcMyNotice;
import com.nateiot.cis.repository.CisCcMyNoticeDao;
import com.nateiot.cis.service.CisCcMyNoticeService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 个人消息公告
 * @author xiewenhua
 *
 */
@Service("CisCcMyNoticeServoce")
@Transactional
public class CisCcMyNoticeServiceImpl extends BaseServiceImpl<CisCcMyNoticeDao, CisCcMyNotice, Integer> implements CisCcMyNoticeService{

	@Autowired
	private CisCcMyNoticeDao cisCcMyNoticeDao;
	
	@Autowired
	public CisCcMyNoticeServiceImpl(CisCcMyNoticeDao d) {
		super(d);
	}

	@Override
	public List<CisCcMyNotice> findByUserIdAndReadStatus(Integer userId,
			Integer readStatus) {
		return cisCcMyNoticeDao.findByUserIdAndReadStatus(userId, readStatus);
	}

	@Override
	public void resetReadStatus(Integer userId, Integer noticeId) {
		List<CisCcMyNotice> list = cisCcMyNoticeDao.findByUserIdAndNoticeId(userId, noticeId);
		if(list.isEmpty()){
			return;
		}
		CisCcMyNotice notice = list.get(0);
		if(notice.getReadStatus() == 1){
			return;
		}
		notice.setReadStatus(1);
		notice.setReadTime(new Date());
		cisCcMyNoticeDao.save(notice);
	}

	
}
