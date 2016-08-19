package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisEsIntegal;
import com.nateiot.core.repository.BaseDao;

/**
 * 考核督办 -- 积分管理
 * 
 * @author Administrator
 *
 */
public interface CisEsIntegalDao extends BaseDao<CisEsIntegal, Integer>,
		CisEsIntegalDaoPlus {

	@Query("select c from CisEsIntegal c where c.integalId in (:integalIds) " )
	public List<CisEsIntegal> queryListById(@Param("integalIds")List<Integer> integalIds);
}
