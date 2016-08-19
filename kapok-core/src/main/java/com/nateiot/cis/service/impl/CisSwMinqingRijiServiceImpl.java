package com.nateiot.cis.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisSwMinqingRiji;
import com.nateiot.cis.repository.CisSwMinqingRijiDao;
import com.nateiot.cis.service.CisSwMinqingRijiService;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 民情日记
 * @author xiewenhua
 *
 */
@Service("CisSwMinqingRijiService")
@Transactional
public class CisSwMinqingRijiServiceImpl extends 
    BaseServiceImpl<CisSwMinqingRijiDao, CisSwMinqingRiji, Integer> implements CisSwMinqingRijiService{

	@Autowired
	private CisSwMinqingRijiDao cisSwMinqingRijiDao;
	
	@Autowired
	public CisSwMinqingRijiServiceImpl(CisSwMinqingRijiDao d) {
		super(d);
	}

	@Override
	public Map<String, Object> doSave(CisSwMinqingRiji entity) {
		//如果日记状态码为1时，表示发布当前日记
		if(1 == entity.getRijiStatus()){
			entity.setSubmitTime(new Date());
		}
		return super.doSave(entity);
	}

	@Override
	public Map<String, Object> doSearchBySql(
			Map<String, SearchFilter> conditions, Pageable pageable) {
		return super.doSearchBySql(conditions, pageable);
	}


	
	

}
