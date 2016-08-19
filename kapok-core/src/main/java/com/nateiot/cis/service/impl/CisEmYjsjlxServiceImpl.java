package com.nateiot.cis.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisEmYjsjlx;
import com.nateiot.cis.repository.CisEmYjsjlxDao;
import com.nateiot.cis.service.CisEmYjsjlxService;
import com.nateiot.core.service.impl.BaseServiceImpl;

/**
 * 应急事件类型
 * @author xiewenhua
 *
 */

@Service(value = "CisEmYjsjlxService")
@Transactional
public class CisEmYjsjlxServiceImpl extends
    BaseServiceImpl<CisEmYjsjlxDao, CisEmYjsjlx, Integer> implements 
    CisEmYjsjlxService {

	@Autowired
	private CisEmYjsjlxDao cisEmYjsjlxDao;
	
	@Autowired
	public CisEmYjsjlxServiceImpl(CisEmYjsjlxDao d) {
		super(d);
	}

	@Override
	public Map<String, Object> doSearch(Specification<CisEmYjsjlx> spec,
			Pageable pageable) {
		resetResultMap();
		List<CisEmYjsjlx> list = cisEmYjsjlxDao.findByParentIdAndDelSign(0, "N");
		List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();
		
		for(int i = 0; i < list.size(); i++){
			Map<String, Object> yjsjlx = toMap(list.get(i));
			readYjsjlx(yjsjlx);
			rows.add(yjsjlx);
		}
		setResultStatus(0, "查询成功");
		resultMap.put("rows", rows);
		return resultMap;
	}

	//递归读取子类型
	private void readYjsjlx(Map<String, Object> map){
		List<CisEmYjsjlx> list = cisEmYjsjlxDao.findByParentIdAndDelSign(
				Integer.parseInt(map.get("yjsjlxId").toString()), "N");
		if(list.isEmpty()){
			return;
		}
		List<Map<String, Object>> children =new ArrayList<Map<String,Object>>();
		for(int i = 0; i < list.size(); i++){
			Map<String, Object> yjsjlx = toMap(list.get(i));
			children.add(yjsjlx);
			readYjsjlx(yjsjlx);
		}
		map.put("children", children);
	}
	
	//将应急类型实体转换成map
	private Map<String, Object> toMap(CisEmYjsjlx yjsjlx){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("yjsjlxId", yjsjlx.getYjsjlxId());
		map.put("color", yjsjlx.getColor());
		map.put("parentId", yjsjlx.getParentId());
		map.put("leixingName", yjsjlx.getLeixingName());
		map.put("leixingPs", yjsjlx.getLeixingPs());
		map.put("createrId", yjsjlx.getCreaterId());
		map.put("fullName", yjsjlx.getFullName());
		map.put("fullPath", yjsjlx.getFullPath());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("createTime", format.format(yjsjlx.getCreateTime()));
		map.put("lastUpdateBy", yjsjlx.getLastUpdateBy());
		map.put("lastUpdateTime", yjsjlx.getLastUpdateTime());
		return map;
	}

	@Override
	@Transactional
	public Map<String, Object> doDelete(Integer id) {
		resetResultMap();
		try {
			delete(id);
			setResultStatus(0, "删除成功");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "删除时系统出错: " + e.getMessage());
			return resultMap;
		}
	}
	
	/**
	 * 删除子类型
	 * @param id
	 */
	private void delete(Integer id){
		List<CisEmYjsjlx> list = cisEmYjsjlxDao.findByParentIdAndDelSign(id, "N");
		//删除子类型
		for(CisEmYjsjlx yjsjlx : list){
			delete(yjsjlx.getYjsjlxId());
		}
		
		//删除当前类型
		super.doDelete(id);
	}

	@Override
	@Transactional
	public Map<String, Object> softDelList(List<Integer> list) {
		resetResultMap();
		try {
			List<CisEmYjsjlx> yjsjlxList = (List<CisEmYjsjlx>) super.doSearch(list).get(RESULT_ROWS);
			for(CisEmYjsjlx yjsjlx : yjsjlxList){
				delete(yjsjlx);
			}
			setResultStatus(0, "删除成功");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "删除时系统出错: " + e.getMessage());
			return resultMap;
		}
	}
	
	/**
	 * 软删除 应急事件类型
	 * @param id
	 */
	private void delete(CisEmYjsjlx entity){
		List<CisEmYjsjlx> list = cisEmYjsjlxDao.findByParentIdAndDelSign(entity.getYjsjlxId(), "N");
		//删除子类型
		for(CisEmYjsjlx yjsjlx : list){
			delete(yjsjlx);
		}
		entity.setDelSign("Y");
		//删除当前类型
		super.doSave(entity);
	}

	@Override
	@Transactional
	public Map<String, Object> doSave(CisEmYjsjlx entity) {
		List<CisEmYjsjlx> entitys = new ArrayList<CisEmYjsjlx>();
		cisEmYjsjlxDao.save(entity);
		getFullEntity(entitys, entity.getYjsjlxId());
		
		//计算当前类型的fullName和fullPath
		String fullName = "", fullPath = "";
		for(int i = entitys.size() - 1; i >= 0 ; i --){
			CisEmYjsjlx yjsjlx = entitys.get(i);
			fullName += yjsjlx.getLeixingName() + ">";
			fullPath += yjsjlx.getYjsjlxId() + ".";
		}
		entity.setFullName(fullName);
		entity.setFullPath(fullPath);
		
		//清空集合，接下来集合保存的将是所有的fullName已经被修改的子孙类型的实体
		entitys.clear();
		entitys.add(entity);
		resetChild(entitys, entity);
		cisEmYjsjlxDao.save(entitys);
		resultMap.put(RESULT_ROW, entity);
		resultMap.put(RESULT_MSG, "保存成功");
		return resultMap;
	}
	
	
	private void resetChild(List<CisEmYjsjlx> childs, CisEmYjsjlx entity){
		List<CisEmYjsjlx> list = cisEmYjsjlxDao.findByParentIdAndDelSign(entity.getYjsjlxId(), "N");
		if(list != null && ! list.isEmpty()){
			for(CisEmYjsjlx yjsjlx : list){
				String fullName = entity.getFullName() + "" + yjsjlx.getLeixingName() + ">";
				yjsjlx.setFullName(fullName);
				childs.add(yjsjlx);
				resetChild(childs, yjsjlx);
			}
		}
	}
	
	
	private void getFullEntity(List<CisEmYjsjlx> fullEntity, Integer id){
		CisEmYjsjlx entity = cisEmYjsjlxDao.findOne(id);
		fullEntity.add(entity);
		if(entity.getParentId() != 0){
			getFullEntity(fullEntity, entity.getParentId());
		}
	}

}
