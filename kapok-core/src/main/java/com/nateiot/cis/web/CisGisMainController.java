package com.nateiot.cis.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gis")
public class CisGisMainController {
	
	//市政设施
	@RequestMapping("/szss")
	public String pageSzss(){
		
		return "cis/gis/ssdw/ssdw"; 
	}
	
	//房屋信息
	@RequestMapping("/house")
	public String pageHouse(){
		
		return "cis/gis/house/gisHouse"; 
	}
	
	
	//机构范围
	@RequestMapping(value = "/meshGis")
	public String pageMeshGis(){
		
		return "cis/gis/mesh/meshGis";
	}
	
	//门店信息
	@RequestMapping(value = "/shop")
	public String pageShopGis(){
		
		return "cis/gis/shop/gisShop";
	}
	
	//学校信息
	@RequestMapping(value = "/school")
	public String pageSchoolGis(){
		
		return "cis/gis/school/gisSchool";
	}
	
	//避难场所信息
	@RequestMapping(value = "/emeplace")
	public String pageEmeplaceGis(){
		
		return "cis/gis/refuge/gisRefuge";
	}
	
	//图标信息
	@RequestMapping(value = "/icon")
	public String pageIconGis(){
		
		return "cis/gis/icon/gisIcon";
	}
}
