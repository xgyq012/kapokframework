package com.nateiot.cis.repository;


import com.nateiot.core.common.persistence.SearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CisBmHealthinSuranceinfoDaoPlus {
    public Page<Map<String, Object>> searchHolderHealthInsurance(Map<String, SearchFilter> conditions, Pageable pageable);
}
