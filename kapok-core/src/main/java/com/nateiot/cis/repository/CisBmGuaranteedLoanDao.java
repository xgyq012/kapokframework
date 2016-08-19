package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmGuaranteedLoan;
import com.nateiot.core.repository.BaseDao;

/**
 * 小额担保贷款
 * 
 * @author Administrator
 *
 */
public interface CisBmGuaranteedLoanDao extends BaseDao<CisBmGuaranteedLoan, Integer>,
         CisBmGuaranteedLoanDaoPlus {

	@Query("select c from CisBmGuaranteedLoan c where c.guaranteedId in (:guaranteedIds) " )
	public List<CisBmGuaranteedLoan> queryListById(@Param("guaranteedIds")List<Integer> guaranteedIds);
}
