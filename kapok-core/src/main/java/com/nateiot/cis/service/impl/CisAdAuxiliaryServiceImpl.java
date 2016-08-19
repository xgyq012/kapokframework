package com.nateiot.cis.service.impl;

import com.nateiot.base.common.SessionUtil;
import com.nateiot.cis.domain.CisAdAuxiliary;
import com.nateiot.cis.repository.CisAdAuxiliaryDao;
import com.nateiot.cis.repository.CisBmCommunityMeshDao;
import com.nateiot.cis.repository.CisEmYjsjlxDao;
import com.nateiot.cis.service.CisAdAuxiliaryService;
import com.nateiot.cis.web.CisBmYjsjclDaoController;
import com.nateiot.core.common.persistence.SearchFilter;
import com.nateiot.core.service.impl.BaseServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.Font;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 辅助决策 
 * 
 */
@Service(value = "cisAdAuxiliaryService")
@Transactional(readOnly = true)
public class CisAdAuxiliaryServiceImpl extends
         BaseServiceImpl<CisAdAuxiliaryDao, CisAdAuxiliary, Integer> implements
         CisAdAuxiliaryService {
	
	@Autowired
	private CisAdAuxiliaryDao cisAdAuxiliaryDao;
	
	@Autowired
	private CisBmCommunityMeshDao cisBmCommunityMeshDao;
	
	@Autowired
	private CisEmYjsjlxDao cisEmYjsjlxDao;
	
	@Autowired
	public CisAdAuxiliaryServiceImpl(CisAdAuxiliaryDao cisAdAuxiliaryDao) {
		super(cisAdAuxiliaryDao);
	}
	 
	
	/**
	 * 综合治理数据统计(查询) 
	 * 
	 * @return
	 */
	@Override
	public Map<String, Object> overHaulFormSearch(
			Map<String, SearchFilter> conditions){
		resetResultMap();
		try{
			List<Map<String, Object>> map = cisAdAuxiliaryDao
					.overHaulFormSearch(conditions);
			resultMap.put(RESULT_ROWS, map);
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询综合治理数据统计成功！");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询综合治理数据统计出错！");
		}
		return resultMap;
	}
	
	/**
	 * 综合治理数据统计(导出报表) 
	 * 
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void overHaulFormExport(Map<String, SearchFilter> conditions,
			HSSFWorkbook workbook){
		Map<String, Object> map =  overHaulFormSearch(conditions);
		
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("rows");
		
		HSSFSheet sheet = workbook.getSheetAt(0);
		sheet.setColumnWidth(0, 18 * 256);
		sheet.setColumnWidth(1, 18 * 256);
		sheet.setColumnWidth(2, 18 * 256);
		sheet.setColumnWidth(3, 18 * 256);
		sheet.setColumnWidth(4, 18 * 256);
		sheet.setColumnWidth(5, 18 * 256);
		sheet.setColumnWidth(6, 18 * 256);
		sheet.setColumnWidth(7, 18 * 256);
		sheet.setColumnWidth(8, 18 * 256);
		
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("综合治理数据统计");
//		row.createCell(1).setCellValue("统计日期：");
//		row.createCell(2).setCellValue(value);
		
		HSSFRow row1 = sheet.createRow(1);
		row1.createCell(0).setCellValue("编号");
		row1.createCell(0).setCellValue("机构名称");
		row1.createCell(0).setCellValue("司法（件）");
		row1.createCell(0).setCellValue("信访（件）");
		row1.createCell(0).setCellValue("巡防队（个）");
		row1.createCell(0).setCellValue("值班室（个）");
		row1.createCell(0).setCellValue("案发数（件");
		
		int i = 2;
		int mark = 1;
		for(Map<String, Object> map2 : list){
			HSSFRow row2 = sheet.createRow(i++);
			row2.createCell(0).setCellValue(mark++);
			row2.createCell(1).setCellValue(
					map2.get("orgName").toString());
			row2.createCell(2).setCellValue(
					map2.get("judicalNum").toString());
			row2.createCell(3).setCellValue(
					map2.get("petotionNum").toString());
			row2.createCell(4).setCellValue(
					map2.get("patrolNum").toString());
			row2.createCell(5).setCellValue(
					map2.get("duryroowNum").toString());
			row2.createCell(6).setCellValue(
					map2.get("caseNum").toString());
					
		}
		
	}


	/**
	 * 人口信息统计
	 */
	public Map<String, Object> rkxxtj(Map<String, SearchFilter> conditions, Pageable pageable) {
		resetResultMap();
		try{
			
			String meshChild = "";
			
			Page<Map<String, Object>> page =  cisAdAuxiliaryDao.rkxxtj(conditions, pageable);
			
			if(conditions!=null && conditions.size()>0){
				SearchFilter f = conditions.get("h.ORG_ID_IN");
				if(f!=null){
					meshChild = f.value==null ? "" : f.value.toString();
				}
			}
			
			List<Map<String, Object>> meshMapList = cisBmCommunityMeshDao.getUserAllMesh(SessionUtil.getCurrentUser().getUserId());
			
			List<Map<String,Object>> list = page.getContent();
			List<Map<String,Object>> listAll = new LinkedList<Map<String,Object>>();
			 
			Map<String,Object> map_d = null;
			
			if(meshMapList.size()>0){
				boolean b = StringUtils.isNotBlank(meshChild);
				for(Map<String, Object> meshMap : meshMapList){
					Integer meshId =Integer.parseInt(meshMap.get("meshId").toString());
					if(!b || (meshChild.indexOf("'"+meshId+"'")!=-1)){
							map_d = new HashMap<String, Object>();
							int rkzs_ = 0;
							int czrk_ = 0;
							int ldrk_ = 0;
							int kgrk_ = 0;
							int dy_ = 0;
							int ssmz_ = 0;
							int lnr_ = 0;
							int lset_ = 0;
						String oString = "."+meshId+".";
						for(Map<String,Object> m : list){
								String fullPath = "."+m.get("fullPath").toString();
								if(fullPath.indexOf(oString)!=-1){
									rkzs_ +=Integer.parseInt(m.get("rkzs").toString());
									czrk_ +=Integer.parseInt(m.get("czrk").toString());
									ldrk_ +=Integer.parseInt(m.get("ldrk").toString());
									kgrk_ +=Integer.parseInt(m.get("kgrk").toString());
									dy_ +=Integer.parseInt(m.get("dy").toString());
									ssmz_ +=Integer.parseInt(m.get("ssmz").toString());
									lnr_ +=Integer.parseInt(m.get("lnr").toString());
									lset_ +=Integer.parseInt(m.get("lset").toString());
								}
								
							}
							
							map_d.put("orgId", meshId);
							map_d.put("rkzs", rkzs_);
							map_d.put("czrk", czrk_);
							map_d.put("ldrk", ldrk_);
							map_d.put("kgrk", kgrk_);
							map_d.put("dy", dy_);
							map_d.put("ssmz", ssmz_);
							map_d.put("lnr", lnr_);
							map_d.put("lset", lset_);
							listAll.add(map_d);
					}
				}
			}
			
			resultMap.put(RESULT_ROWS, listAll);
			resultMap.put(RESULT_TOTAL, listAll.size());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功。");
			 
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		
		return resultMap;
	}

	
 

	/**
	 * 房屋管理统计
	 */
	@Override
	public Map<String, Object> fwgltj(Map<String, SearchFilter> conditions, Pageable pageable) {
		resetResultMap();
		try{
			
			Page<Map<String, Object>> page =  cisAdAuxiliaryDao.fwgltj(conditions, pageable);

			String meshChild = "";
			
			if(conditions!=null && conditions.size()>0){
				SearchFilter f = conditions.get("t.ORG_IN");
				if(f!=null){
					meshChild = f.value==null ? "" : f.value.toString();
				}
			}
			
			
			List<Map<String, Object>> meshMapList = cisBmCommunityMeshDao.getUserAllMesh(SessionUtil.getCurrentUser().getUserId());
			
			List<Map<String,Object>> list = page.getContent();
			
			List<Map<String,Object>> listAll = new LinkedList<Map<String,Object>>();
			
			Map<String,Object> map_d = null;
			
			if(meshMapList.size()>0){
				boolean b = StringUtils.isNotBlank(meshChild);
				for(Map<String, Object> meshMap : meshMapList){
					Integer meshId =Integer.parseInt(meshMap.get("meshId").toString());
					if(!b || (meshChild.indexOf("'"+meshId+"'")!=-1)){
						map_d = new HashMap<String, Object>();
						int building =0;
						int community =0;
						int house =0;
						String oString = "."+meshId+".";
						for(Map<String,Object> m : list){
							String fullPath = "."+m.get("fullPath").toString();
							if(fullPath.indexOf(oString)!=-1){
								building +=Integer.parseInt(m.get("building").toString());
								community +=Integer.parseInt(m.get("community").toString());
								house +=Integer.parseInt(m.get("house").toString());
							}
						}
						map_d.put("orgId", meshId);
						map_d.put("building", building);
						map_d.put("community", community);
						map_d.put("house", house);
						listAll.add(map_d);
					}
				}
			
			}
			
			resultMap.put(RESULT_ROWS,listAll);
			resultMap.put(RESULT_TOTAL, page.getTotalElements());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功。");
			 
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		
		return resultMap;
	}

	/**
	 * 人员事件处理统计
	 */
	@Override
	public Map<String, Object> event(Map<String, SearchFilter> conditions,Pageable pageable) {
		resetResultMap();
		try{
			
			String meshChild = "";
			
			Page<Map<String, Object>> page =  cisAdAuxiliaryDao.event(conditions, pageable);
			
			if(conditions!=null && conditions.size()>0){
				SearchFilter f = conditions.get("r.BACKLOG_ID_IN");
				if(f!=null){
					meshChild = f.value==null ? "" : f.value.toString();
				}
			}
			
			List<Map<String, Object>> meshMapList = cisBmCommunityMeshDao.getUserAllMesh(SessionUtil.getCurrentUser().getUserId());
			
			List<Map<String,Object>> list = page.getContent();
			List<Map<String,Object>> listAll = new LinkedList<Map<String,Object>>();
			 
			Map<String,Object> map_d = null;
			
			if(meshMapList.size()>0){
				boolean b = StringUtils.isNotBlank(meshChild);
				for(Map<String, Object> meshMap : meshMapList){
					Integer meshId =Integer.parseInt(meshMap.get("meshId").toString());
					if(!b || (meshChild.indexOf("'"+meshId+"'")!=-1)){
							map_d = new HashMap<String, Object>();
							int number = 0;
							int submitSum = 0;
							int assign = 0;
							int sign = 0;
							int dispose = 0;
							int evaluate = 0;
						String oString = "."+meshId+".";
						for(Map<String,Object> m : list){
								String fullPath = "."+m.get("fullPath").toString();
								if(fullPath.indexOf(oString)!=-1){
									number +=Integer.parseInt(m.get("number").toString());
									submitSum +=Integer.parseInt(m.get("submitSum").toString());
									assign +=Integer.parseInt(m.get("assign").toString());
									sign +=Integer.parseInt(m.get("sign").toString());
									dispose +=Integer.parseInt(m.get("dispose").toString());
									evaluate +=Integer.parseInt(m.get("evaluate").toString());
								}
								
						}
							
						map_d.put("orgId", meshId);
						map_d.put("number", number);
						map_d.put("submitSum", submitSum);
						map_d.put("assign", assign);
						map_d.put("sign", sign);
						map_d.put("dispose", dispose);
						map_d.put("evaluate", evaluate);
						map_d.put("fullPath", meshMap.get("fullPath").toString());
						listAll.add(map_d);
					}
				}
			}
			
			resultMap.put(RESULT_ROWS, listAll);
			resultMap.put(RESULT_TOTAL, listAll.size());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功。");
			 
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		
		return resultMap;
	}

	/**
	 * 党群建设统计
	 */
	@Override
	public Map<String, Object> party(Map<String, SearchFilter> conditions,
			Pageable pageable) {
		resetResultMap();
		try{
			
			String meshChild = "";
			
			Page<Map<String, Object>> page =  cisAdAuxiliaryDao.party(conditions, pageable);
			
			if(conditions!=null && conditions.size()>0){
				SearchFilter f = conditions.get("t.ORG_IN");
				if(f!=null){
					meshChild = f.value==null ? "" : f.value.toString();
				}
			}
			
			List<Map<String, Object>> meshMapList = cisBmCommunityMeshDao.getUserAllMesh(SessionUtil.getCurrentUser().getUserId());
			
			List<Map<String,Object>> list = page.getContent();
			List<Map<String,Object>> listAll = new LinkedList<Map<String,Object>>();
			 
			Map<String,Object> map_d = null;
			
			if(meshMapList.size()>0){
				boolean b = StringUtils.isNotBlank(meshChild);
				for(Map<String, Object> meshMap : meshMapList){
					Integer meshId =Integer.parseInt(meshMap.get("meshId").toString());
					//Integer sum =Integer.parseInt(meshMap.get("sum").toString());
					if(!b || (meshChild.indexOf("'"+meshId+"'")!=-1)){
						
							map_d = new HashMap<String, Object>();
							int organization = 0;
							int partyMember = 0;
							int aborUnion = 0;
							int member = 0;
							int nspection = 0;
							int womanCadres = 0;
						String oString = "."+meshId+".";
						for(Map<String,Object> m : list){
								String fullPath = "."+m.get("fullPath").toString();
								if(fullPath.indexOf(oString)!=-1){
									organization +=Integer.parseInt(m.get("organization").toString());
									partyMember +=Integer.parseInt(m.get("partyMember").toString());
									aborUnion +=Integer.parseInt(m.get("aborUnion").toString());
									member +=Integer.parseInt(m.get("member").toString());
									nspection +=Integer.parseInt(m.get("nspection").toString());
									womanCadres +=Integer.parseInt(m.get("womanCadres").toString());
								}
								
						}
							
						map_d.put("orgId", meshId);
						map_d.put("organization", organization);
						map_d.put("partyMember", partyMember);
						map_d.put("aborUnion", aborUnion);
						map_d.put("member", member);
						map_d.put("nspection", nspection);
						map_d.put("womanCadres", womanCadres);
						map_d.put("fullPath", meshMap.get("fullPath").toString());
						listAll.add(map_d);
					}
				}
			}
			
			resultMap.put(RESULT_ROWS, listAll);
			resultMap.put(RESULT_TOTAL, listAll.size());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功。");
			 
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		
		return resultMap;
	}

	/**
	 * 应急管理统计
	 */
	@Override
	public Map<String, Object> emergency(Map<String, SearchFilter> conditions,
		Pageable pageable) {
		resetResultMap();
		try{
			
			String meshChild = "";
			
			Page<Map<String, Object>> page =  cisAdAuxiliaryDao.emergency(conditions, pageable);
			
			if(conditions!=null && conditions.size()>0){
				SearchFilter f = conditions.get("h.YJSJLX_ID_EQ");
				if(f!=null){
					meshChild = f.value==null ? "" : f.value.toString();
				}
			}
			
			List<Map<String, Object>> emergencyMap = cisEmYjsjlxDao.getEmergency();
			
			List<Map<String,Object>> list = page.getContent();
			List<Map<String,Object>> listAll = new LinkedList<Map<String,Object>>();
			 
			Map<String,Object> map_d = null;
			
			if(emergencyMap.size()>0){
				boolean b = StringUtils.isNotBlank(meshChild);
				for(Map<String, Object> meshMap : emergencyMap){
					Integer meshId =Integer.parseInt(meshMap.get("yjsjlxId").toString());
					String name = meshMap.get("leixingName").toString();
				
					if(!b || (meshChild.indexOf("'"+meshId+"'")!=-1) ){
						map_d = new HashMap<String, Object>();
						int member = 0;
						int draft = 0;
						int isSubmit = 0;
						int inspect = 0;
						int nvestigate = 0;
						int followUp = 0;
						String oString = "."+meshId+".";
						for(Map<String,Object> m : list){
								String fullPath = "."+m.get("fullPath").toString();
								if(fullPath.indexOf(oString)!=-1){
									member +=Integer.parseInt(m.get("member").toString());
									draft +=Integer.parseInt(m.get("draft").toString());
									isSubmit +=Integer.parseInt(m.get("isSubmit").toString());
									inspect +=Integer.parseInt(m.get("inspect").toString());
									nvestigate +=Integer.parseInt(m.get("nvestigate").toString());
									followUp +=Integer.parseInt(m.get("followUp").toString());
									
								}
								
						}
						
						map_d.put("name",name);
						map_d.put("yjsjlxId", meshId);
						map_d.put("member", member);
						map_d.put("draft", draft);
						map_d.put("isSubmit", isSubmit);
						map_d.put("inspect", inspect);
						map_d.put("nvestigate", nvestigate);
						map_d.put("followUp", followUp);
						map_d.put("fullPath", meshMap.get("fullPath").toString());
						listAll.add(map_d);
					}
				}
			}
			
			resultMap.put(RESULT_ROWS, listAll);
			resultMap.put(RESULT_TOTAL, listAll.size());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功。");
			 
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		
		return resultMap;
	}

	/**
	 * 机构人员统计查询
	 */
	@Override
	public Map<String, Object> orgPeople(Map<String, SearchFilter> conditions,
			Pageable pageable) {
		resetResultMap();
		try{
			
			String meshChild = "";
			
			Page<Map<String, Object>> page =  cisAdAuxiliaryDao.orgPeople(conditions, pageable);
			
			if(conditions!=null && conditions.size()>0){
				SearchFilter f = conditions.get("m.MESH_ID_IN");
				if(f!=null){
					meshChild = f.value==null ? "" : f.value.toString();
				}
			}
			
			List<Map<String, Object>> meshMapList = cisBmCommunityMeshDao.getUserAllMesh(SessionUtil.getCurrentUser().getUserId());
			
			List<Map<String,Object>> list = page.getContent();
			List<Map<String,Object>> listAll = new LinkedList<Map<String,Object>>();
			 
			Map<String,Object> map_d = null;
			
			if(meshMapList.size()>0){
				boolean b = StringUtils.isNotBlank(meshChild);
				for(Map<String, Object> meshMap : meshMapList){
					Integer meshId =Integer.parseInt(meshMap.get("meshId").toString());
					//System.out.println(meshId);
					if(!b || (meshChild.indexOf("'"+meshId+"'")!=-1)){
						
							map_d = new HashMap<String, Object>();
							int member = 0;
						String oString = "."+meshId+".";
						String fullPath = "";
						
						for(Map<String,Object> m : list){
								fullPath = "."+m.get("fullPath").toString();
						
								if(fullPath.indexOf(oString)!=-1){
									member +=Integer.parseInt(m.get("member").toString());
								}
								
						}
						
						map_d.put("orgId", meshId);
						map_d.put("fullPath", meshMap.get("fullPath").toString());
						//map_d.put("fullPath", fullPath);
						map_d.put("member", member);
						listAll.add(map_d);
					}
				}
			}
			
			resultMap.put(RESULT_ROWS, listAll);
			resultMap.put(RESULT_TOTAL, listAll.size());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功。");
			 
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		
		return resultMap;
	}

	/**
	 * 特殊人群统计
	 */
	@Override
	public Map<String, Object> crowd(Map<String, SearchFilter> conditions,
		Pageable pageable) {
		resetResultMap();
		try{
			
			String meshChild = "";
			
			Page<Map<String, Object>> page =  cisAdAuxiliaryDao.crowd(conditions, pageable);
			
			if(conditions!=null && conditions.size()>0){
				SearchFilter f = conditions.get("t.ORG_IN");
				if(f!=null){
					meshChild = f.value==null ? "" : f.value.toString();
				}
			}
			
			List<Map<String, Object>> meshMapList = cisBmCommunityMeshDao.getUserAllMesh(SessionUtil.getCurrentUser().getUserId());
			
			List<Map<String,Object>> list = page.getContent();
			List<Map<String,Object>> listAll = new LinkedList<Map<String,Object>>();
			 
			Map<String,Object> map_d = null;
			
			if(meshMapList.size()>0){
				boolean b = StringUtils.isNotBlank(meshChild);
				for(Map<String, Object> meshMap : meshMapList){
					Integer meshId =Integer.parseInt(meshMap.get("meshId").toString());
					if(!b || (meshChild.indexOf("'"+meshId+"'")!=-1)){
							map_d = new HashMap<String, Object>();
							int sum = 0;
							int children = 0;
							int teenager = 0;
							int heresy = 0;
							int punishment = 0;
							int drugs = 0;
							int rectify = 0;
						String oString = "."+meshId+".";
						for(Map<String,Object> m : list){
								String fullPath = "."+m.get("fullPath").toString();
								if(fullPath.indexOf(oString)!=-1){
									sum +=Integer.parseInt(m.get("sum").toString());
									children +=Integer.parseInt(m.get("children").toString());
									teenager +=Integer.parseInt(m.get("teenager").toString());
									heresy +=Integer.parseInt(m.get("heresy").toString());
									punishment +=Integer.parseInt(m.get("punishment").toString());
									drugs +=Integer.parseInt(m.get("drugs").toString());
									rectify +=Integer.parseInt(m.get("rectify").toString());
								}
								
						}
							
						map_d.put("orgId", meshId);
						map_d.put("sum", sum);
						map_d.put("children", children);
						map_d.put("teenager", teenager);
						map_d.put("heresy", heresy);
						map_d.put("punishment", punishment);
						map_d.put("drugs", drugs);
						map_d.put("rectify", rectify);
						map_d.put("fullPath", meshMap.get("fullPath").toString());
						listAll.add(map_d);
					}
				}
			}
			
			resultMap.put(RESULT_ROWS, listAll);
			resultMap.put(RESULT_TOTAL, listAll.size());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功。");
			 
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		
		return resultMap;
	}

	/**
	 * 综合治理统计
	 */
	@Override
	public Map<String, Object> govern(Map<String, SearchFilter> conditions,
		Pageable pageable) {
		resetResultMap();
		try{
			
			String meshChild = "";
			
			Page<Map<String, Object>> page =  cisAdAuxiliaryDao.govern(conditions, pageable);
			
			if(conditions!=null && conditions.size()>0){
				SearchFilter f = conditions.get("t.ORG_IN");
				if(f!=null){
					meshChild = f.value==null ? "" : f.value.toString();
				}
			}
			
			List<Map<String, Object>> meshMapList = cisBmCommunityMeshDao.getUserAllMesh(SessionUtil.getCurrentUser().getUserId());
			
			List<Map<String,Object>> list = page.getContent();
			List<Map<String,Object>> listAll = new LinkedList<Map<String,Object>>();
			 
			Map<String,Object> map_d = null;
			
			if(meshMapList.size()>0){
				boolean b = StringUtils.isNotBlank(meshChild);
				for(Map<String, Object> meshMap : meshMapList){
					Integer meshId =Integer.parseInt(meshMap.get("meshId").toString());
					if(!b || (meshChild.indexOf("'"+meshId+"'")!=-1)){
							map_d = new HashMap<String, Object>();
							int judicial = 0;
							int petition = 0;
							int details = 0;
							int patrol = 0;
							int technical = 0;
							int duty = 0;
						String oString = "."+meshId+".";
						for(Map<String,Object> m : list){
								String fullPath = "."+m.get("fullPath").toString();
								if(fullPath.indexOf(oString)!=-1){
									judicial +=Integer.parseInt(m.get("judicial").toString());
									petition +=Integer.parseInt(m.get("petition").toString());
									details +=Integer.parseInt(m.get("details").toString());
									patrol +=Integer.parseInt(m.get("patrol").toString());
									technical +=Integer.parseInt(m.get("technical").toString());
									duty +=Integer.parseInt(m.get("duty").toString());
								}
								
						}
							
						map_d.put("orgId", meshId);
						map_d.put("judicial", judicial);
						map_d.put("petition", petition);
						map_d.put("details", details);
						map_d.put("patrol", patrol);
						map_d.put("technical", technical);
						map_d.put("duty", duty);
						map_d.put("fullPath", meshMap.get("fullPath").toString());
						listAll.add(map_d);
					}
				}
			}
			
			resultMap.put(RESULT_ROWS, listAll);
			resultMap.put(RESULT_TOTAL, listAll.size());
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "查询成功。");
			 
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "查询出错");
		}
		
		return resultMap;
	}

	
}
