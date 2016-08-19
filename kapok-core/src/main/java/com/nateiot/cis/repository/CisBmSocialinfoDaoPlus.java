package com.nateiot.cis.repository;

import com.nateiot.core.common.persistence.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CisBmSocialinfoDaoPlus {

    /**
     * 获取存在社保信息的人员
     * @param conditions
     * @param pageable
     * @return
     */
    public Page<Map<String, Object>> searchHolderSocialinfo(Map<String, SearchFilter> conditions, Pageable pageable);

}
