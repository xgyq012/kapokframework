package com.nateiot.cis.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.nateiot.cis.domain.CisGisPublicFacilities;
import com.nateiot.core.repository.BaseDao;

public interface CisGisFacilitiesDao extends BaseDao<CisGisPublicFacilities,Integer>,CisGisFacilitiesDaoPlus {
	@Query("select c from  CisGisPublicFacilities c where c.facilitiesId in (:ids) " )
	public List<CisGisPublicFacilities> queryListById(@Param("ids")List<Integer> ids);

}
