package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmPublicSecurity;
import com.nateiot.core.repository.BaseDao;

/**
 * 治安信息
 * 
 * @author Administrator
 *
 */
public interface CisBmPublicSecurityDao extends BaseDao<CisBmPublicSecurity, Integer>,
         CisBmPublicSecurityDaoPlus {
	
	@Query("select c from CisBmPublicSecurity c where c.pusecurityId in (:pusecurityId) " )
	public List<CisBmPublicSecurity> queryListById(@Param("pusecurityId")List<Integer> pusecurityId);

}
