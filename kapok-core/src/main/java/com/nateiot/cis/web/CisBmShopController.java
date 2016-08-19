package com.nateiot.cis.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmShop;
import com.nateiot.cis.service.CisBmShopService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 门店信息
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/shop")
public class CisBmShopController {

    @Autowired
    private CisBmShopService cisBmShopService;

    /**
     * 加载视图
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "cis/bm/shop/list";
    }


    /**
     * 查询
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/search")
    @ResponseBody
    public Map<String, Object> search(HttpServletRequest req) {

        return cisBmShopService.doSearch(
                SearchUtil.getSpecification(CisBmShop.class, req),
                SearchUtil.getPageableWithOrderBy(req, "createTime_desc"));
    }

    /**
     * 保存
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Map<String, Object> save(CisBmShop cisBmShop) {
        return cisBmShopService.doSave(cisBmShop);
    }

    /**
     * 详细
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/get/{shopId}")
    @ResponseBody
    public Map<String, Object> get(
            @PathVariable(value = "shopId") Integer shopId) {
        return cisBmShopService.doSelect(shopId);
    }

    /**
     * 软删除记录
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/softDel/{shopId}")
    @ResponseBody
    public Map<String, Object> softDel(
            @PathVariable(value = "shopId") Integer shopId) {
        return cisBmShopService.softDel(shopId);
    }

    /**
     * 硬删除记录
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/del/{shopId}")
    @ResponseBody
    public Map<String, Object> del(
            @PathVariable(value = "shopId") Integer shopId) {
        return cisBmShopService.doDelete(shopId);
    }


    /**
     * 硬批量删除记录
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delList(@RequestParam(value = "ids") String ids) {

        List<Integer> list = null;
        if (StringUtils.isNotBlank(ids)) {
            list = new ArrayList<Integer>();
            String[] arry = ids.split(",");
            for (String id : arry) {
                if (id != null) {
                    list.add(Integer.parseInt(id));
                }
            }
        }

        return cisBmShopService.delList(list);
    }
    
    /**
	 * 门店查询
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/queryShop")
	@ResponseBody
	public Map<String, Object> queryShop(HttpServletRequest req){
		
		return  cisBmShopService.queryShop(SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, ""));
	}

}
