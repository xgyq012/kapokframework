package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisEsSupervision;
import com.nateiot.core.repository.BaseDao;

/**
 * 考核督办 -- 事件督办
 * 
 * @author Administrator
 *
 */
public interface CisEsSupervisionDao extends BaseDao<CisEsSupervision, Integer>,
		CisEsSupervisionDaoPlus {

	@Query("select c from CisEsSupervision c where c.supervisionId in (:supervisionIds) " )
	public List<CisEsSupervision> queryListById(@Param("supervisionIds")List<Integer> supervisionIds);
}
