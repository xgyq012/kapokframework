package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisEmEmePlace;
import com.nateiot.cis.domain.CisEmYingjiRenyuan;
import com.nateiot.core.repository.BaseDao;

/**
 * 应急人员
 * @author xiewenhua
 *
 */
public interface CisEmYingjiRenyuanDao extends BaseDao<CisEmYingjiRenyuan, Integer>, CisEmYingjiRenyuanDaoPlus {
	@Query("select c from CisEmYingjiRenyuan c where c.yingjiRenyuanId in (:ids) " )
	public List<CisEmYingjiRenyuan> queryListById(@Param("ids")List<Integer> ids);
}
