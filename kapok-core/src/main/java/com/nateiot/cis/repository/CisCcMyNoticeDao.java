package com.nateiot.cis.repository;

import java.util.List;

import com.nateiot.cis.domain.CisCcMyNotice;
import com.nateiot.core.repository.BaseDao;

public interface CisCcMyNoticeDao extends BaseDao<CisCcMyNotice, Integer>, CisCcMyNoticeDaoPlus{
	public List<CisCcMyNotice> findByUserIdAndReadStatus(Integer userId, Integer readStatus);
	public List<CisCcMyNotice> findByUserIdAndNoticeId(Integer userId, Integer noticeId);

}
