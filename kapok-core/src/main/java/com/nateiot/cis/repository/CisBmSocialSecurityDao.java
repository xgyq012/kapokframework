package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmSocialSecurity;
import com.nateiot.core.repository.BaseDao;

/**
 * 社保信息
 * 
 * @author Administrator
 *
 */
public interface CisBmSocialSecurityDao extends BaseDao<CisBmSocialSecurity, Integer>,
         CisBmSocialSecurityDaoPlus {
	
	@Query("select c from  CisBmSocialSecurity c where c.socialSecurityId in (:socialSecurityIds) " )
	public List<CisBmSocialSecurity> queryListById(@Param("socialSecurityIds")List<Integer> socialSecurityIds);

}
