package com.nateiot.core.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.nateiot.cis.service.impl.CisSwWaiterServiceImpl;

public class ViewDataHelp {
	@Autowired
	private CisSwWaiterServiceImpl waiterService;
	
	public static Object getWaiter(Integer id){
		System.out.println("+++++++++++++++++++++++++++++++++++++"+id);
		return null;
	}

}
