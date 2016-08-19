package com.nateiot.cis.service;

import java.util.List;

import com.nateiot.cis.domain.CisCcMyNotice;
import com.nateiot.cis.repository.CisCcMyNoticeDao;
import com.nateiot.core.service.BaseService;

public interface CisCcMyNoticeService extends BaseService<CisCcMyNoticeDao, CisCcMyNotice, Integer>{
	public List<CisCcMyNotice> findByUserIdAndReadStatus(Integer userId, Integer readStatus);
	public void resetReadStatus(Integer userId, Integer noticeId);

}
