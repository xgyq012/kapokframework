package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import com.nateiot.cis.domain.CisCcNotice;
import com.nateiot.cis.repository.CisCcNoticeDao;
import com.nateiot.core.service.BaseService;

public interface CisCcNoticeService extends BaseService<CisCcNoticeDao, CisCcNotice, Integer> {
	public Map<String, Object> softDel(List<Integer> ids);
}
