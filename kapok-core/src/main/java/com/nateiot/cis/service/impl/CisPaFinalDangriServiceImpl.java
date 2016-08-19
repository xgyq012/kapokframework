package com.nateiot.cis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.cis.domain.CisPaDangriMingxi;
import com.nateiot.cis.domain.CisPaFinalDangri;
import com.nateiot.cis.domain.CisPaProperty;
import com.nateiot.cis.repository.CisPaDangriMingxiDao;
import com.nateiot.cis.repository.CisPaFinalDangriDao;
import com.nateiot.cis.service.CisPaFinalDangriService;
import com.nateiot.core.service.impl.BaseServiceImpl;

@Service("CisPaFinalDangriService")
@Transactional
public class CisPaFinalDangriServiceImpl extends BaseServiceImpl<CisPaFinalDangriDao, CisPaFinalDangri, Integer> 
	implements CisPaFinalDangriService{

	@Autowired
	private CisPaFinalDangriDao cisPaFinalDangriDao;
	
	@Autowired
	private CisPaDangriMingxiDao drmxDao;
	
	@Autowired
	public CisPaFinalDangriServiceImpl(CisPaFinalDangriDao d) {
		super(d);
	}

	@Override
	@Transactional
	public Map<String, Object> doSave(CisPaFinalDangri entity) {
		try{
			if(entity.getFinalDangriId() == null){
				entity.setDelSign("N");
				cisPaFinalDangriDao.save(entity);
				List<CisPaDangriMingxi> list = entity.getDangriMingxiList();
				if(list != null && ! list.isEmpty()){
					for(CisPaDangriMingxi drmx : list){
						drmx.setFinalDangriId(entity.getFinalDangriId());
					}
				}
			}else{
				CisPaFinalDangri fd = cisPaFinalDangriDao.findOne(entity.getFinalDangriId());
				List<CisPaDangriMingxi> list = fd.getDangriMingxiList();
				List<CisPaDangriMingxi> newMxList = entity.getDangriMingxiList();
				if(newMxList == null){
					drmxDao.deleteInBatch(list);
				}else{
					
					List<CisPaDangriMingxi> delList = new ArrayList<CisPaDangriMingxi>();
					
					//找出被删除的明细记录保存到delList集合中，等待删除
					for(CisPaDangriMingxi mx : list){
						boolean yesDel = true;
						if(newMxList != null){
							for(CisPaDangriMingxi nmx : newMxList){
								if(nmx.getDangriMingxiId() != null 
										&& nmx.getDangriMingxiId().intValue() == mx.getDangriMingxiId().intValue()){
									yesDel = false;
									break;
								}
							}
						}

						if(yesDel){
							delList.add(mx);
						}
					}
					
					if(! delList.isEmpty()){
						drmxDao.deleteInBatch(delList);
					};
					
					for(CisPaDangriMingxi nmx : newMxList){
						if(nmx.getFinalDangriId() == null){
							nmx.setFinalDangriId(entity.getFinalDangriId());
						}
					}
				}
			}
	
			resultMap.put(RESULT_ROW, cisPaFinalDangriDao.save(entity));
			resultMap.put(RESULT_CODE, 0);
			resultMap.put(RESULT_MSG, "保存成功！");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "保存出错！");
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> softDelList(List<Integer> ids) {
		try{
			resetResultMap();
			for(Integer id : ids){
				CisPaFinalDangri entity = cisPaFinalDangriDao.findOne(id);
				entity.setDelSign("Y");
				cisPaFinalDangriDao.save(entity);
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(RESULT_CODE, -1);
			resultMap.put(RESULT_MSG, "删除出错");
		}
		return resultMap;
	}

	
	
}
