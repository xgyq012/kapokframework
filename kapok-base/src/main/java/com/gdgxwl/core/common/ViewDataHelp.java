package com.gdgxwl.core.common;

import org.springframework.beans.factory.annotation.Autowired;


public class ViewDataHelp {
	@Autowired

	public static Object getWaiter(Integer id){
		System.out.println("+++++++++++++++++++++++++++++++++++++"+id);
		return null;
	}

}
