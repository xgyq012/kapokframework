package com.nateiot.cis.repository;

import java.util.List;

import com.nateiot.cis.domain.CisSwTeamMember;
import com.nateiot.core.repository.BaseDao;

/**
 * 服务办事 -- 服务团队行表
 * 
 *  @author Guohw
 */
public interface CisSwTeamMemberDao extends BaseDao<CisSwTeamMember, Integer>,
		CisSwTeamMemberDaoPlus{

//	List<CisSwTeamMember> findByTeamId(Integer teamId);

}
