package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisPaPartyRegime;
import com.nateiot.core.repository.BaseDao;

/**
 * 党务建设 -- 党务公开制度
 * 
 * @author Guohw
 *
 */
public interface CisPaPartyRegimeDao extends BaseDao<CisPaPartyRegime, Integer>,
		CisPaPartyRegimeDaoPlus {
	
	@Query("select c from CisPaPartyRegime c where c.regimeId in (:regimeIds) " )
	public List<CisPaPartyRegime> queryListById(@Param("regimeIds")List<Integer> regimeIds);

}
