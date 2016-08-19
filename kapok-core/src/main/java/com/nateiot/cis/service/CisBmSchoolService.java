package com.nateiot.cis.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.nateiot.cis.domain.CisBmSchool;
import com.nateiot.cis.repository.CisBmSchoolDao;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.BaseService;

/**
 * 学校信息
 * 
 * @author Administrator
 *
 */
public interface CisBmSchoolService extends
	BaseService<CisBmSchoolDao, CisBmSchool, Integer> {
	
	public Map<String, Object> softDel(Integer schoolId);
	
	public Map<String, Object> delList(List<Integer> ids);	
	
	public Map<String,Object>  querySchool(Map<String, SearchFilter> conditions, Pageable pageable);
}
