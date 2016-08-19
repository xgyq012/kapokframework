package com.nateiot.cis.repository;

import java.util.List;
import java.util.Map;

public interface CisBmCommunityMsgDaoPlus {

	public List<Map<String,Object>> getExistsCommunityForBuildORHouse(Integer comId);
	
}
