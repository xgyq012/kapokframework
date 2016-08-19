package com.nateiot.cis.repository;

import java.util.List;

import com.nateiot.cis.domain.CisEmShencha;
import com.nateiot.core.repository.BaseDao;

public interface CisEmShenchaDao extends BaseDao<CisEmShencha, Integer>, CisEmShenchaDaoPlus{
	public List<CisEmShencha> findByYingjiShijianId(Integer yingjiShijianId);

}
