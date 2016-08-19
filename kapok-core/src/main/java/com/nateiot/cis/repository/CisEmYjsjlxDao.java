package com.nateiot.cis.repository;

import java.util.List;

import com.nateiot.cis.domain.CisEmYjsjlx;
import com.nateiot.cis.repository.CisEmYjsjlxDaoPlus;
import com.nateiot.core.repository.BaseDao;

/**
 * 应急事件类型
 * @author xiewenhua
 *
 */
public interface CisEmYjsjlxDao extends BaseDao<CisEmYjsjlx, Integer>, CisEmYjsjlxDaoPlus{
	public List<CisEmYjsjlx> findByParentIdAndDelSign(Integer parentId, String delSign);

}
