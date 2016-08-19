package com.nateiot.cis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nateiot.cis.domain.CisBmCultivate;
import com.nateiot.core.repository.BaseDao;

/**
 * 劳动保障--培训
 * 
 * @author Administrator
 *
 */
public interface CisBmCultivateDao extends BaseDao<CisBmCultivate, Integer>,
         CisBmCultivateDaoPlus {
	@Query("select c from  CisBmCultivate c where c.cultivateId in (:cultivateIds) " )
	public List<CisBmCultivate> queryListById(@Param("cultivateIds")List<Integer> cultivateIds);
	

}
