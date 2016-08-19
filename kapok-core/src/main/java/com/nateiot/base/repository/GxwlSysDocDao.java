package com.nateiot.base.repository;

import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.cis.domain.CisBmBuildingMsg;
import com.nateiot.core.repository.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Will WM. Zhang
 *
 */
public interface GxwlSysDocDao extends BaseDao<GxwlSysDoc, Integer>,
		GxwlSysDocDaoPlus {

	public GxwlSysDoc findByDocIdAndIsTemp(Integer docId, String isTemp);

	@Query("select c from  GxwlSysDoc c where c.docId in (:ids) " )
	public List<GxwlSysDoc> queryListById(@Param("ids")List<Integer> ids);

}
