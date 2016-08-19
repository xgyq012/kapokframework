package com.nateiot.cis.repository;

import java.util.List;

import com.nateiot.cis.domain.CisEmHecha;
import com.nateiot.core.repository.BaseDao;

public interface CisEmHechaDao extends BaseDao<CisEmHecha, Integer>, CisEmHechaDaoPlus{
	public List<CisEmHecha> findByYingjiShijianId(Integer yingjiShijianId);

}
