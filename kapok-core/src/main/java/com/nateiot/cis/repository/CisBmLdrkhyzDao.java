package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmLdrkhyz;
import com.nateiot.core.repository.BaseDao;

public interface CisBmLdrkhyzDao extends BaseDao<CisBmLdrkhyz, Integer> , CisBmLdrkhyzDaoPlus {

	@Query("select c from  CisBmLdrkhyz c where c.ldId in (:ids) " )
	public List<CisBmLdrkhyz> queryListById(@Param("ids")List<Integer> ids);
	
}
