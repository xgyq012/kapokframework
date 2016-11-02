package com.gdgxwl.base.service.impl;

import com.gdgxwl.base.common.BaseUtil;
import com.gdgxwl.base.common.SessionUtil;
import com.gdgxwl.base.domain.GxwlSysDoc;
import com.gdgxwl.base.repository.GxwlSysDocDao;
import com.gdgxwl.base.service.GxwlSysDocService;
import com.gdgxwl.core.common.ContentType;
import com.gdgxwl.core.service.impl.BaseServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 *
 */
@Service(value = "gxwlSysDocService")
@Transactional(readOnly = true) 
public class GxwlSysDocServiceImpl extends
		BaseServiceImpl<GxwlSysDocDao, GxwlSysDoc, Integer> implements
		GxwlSysDocService {

	@Autowired
	private GxwlSysDocDao gxwlSysDocDao;

	@Autowired
	public GxwlSysDocServiceImpl(GxwlSysDocDao gxwlSysDocDao) {
		super(gxwlSysDocDao);
	}

	@Override
	public GxwlSysDoc saveDoc(MultipartFile file, String directory) {
		byte[] filedata = null;
		try {
			filedata = file.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return this.saveDoc(filedata, directory, "Y",
				file.getOriginalFilename(), null);
	}

	@Override
	public GxwlSysDoc saveDoc(byte[] filedata, String directory, Integer userId) {
		return this.saveDoc(filedata, directory, "N", null, userId);
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public GxwlSysDoc saveDoc(byte[] filedata, String directory, String isTemp,
			String originalFilename, Integer userId) {

		String dir = StringUtils.trimToEmpty(directory);
		dir = StringUtils.stripStart(dir, "/");
		dir = StringUtils.stripEnd(dir, "/");
		dir = StringUtils.isEmpty(dir) ? "/" : "/".concat(dir).concat("/");

		GxwlSysDoc doc = null;
		String docShowname = null;
		String docFullname = null;
		String docExtension = null;
		String docUri = null;
		Integer createBy = userId == null ? SessionUtil.getCurrentUser().getUserId() : userId;
		Integer lastUpdateBy = createBy;

		if (StringUtils.isEmpty(originalFilename)) {
			docShowname = String.valueOf(DateTime.now().getMillis());
			docFullname = docShowname;
			docExtension = StringUtils.EMPTY;
			docUri = dir.concat(docFullname);
		} else {
			docShowname = originalFilename;
			docExtension = StringUtils.substringAfterLast(originalFilename, ".");
			docFullname = String.valueOf(DateTime.now().getMillis()).concat(".").concat(docExtension);
			docUri = dir.concat(docFullname);
		}

		String filePath = BaseUtil.getSysRootPath().concat(docUri);
		File file = new File(filePath);

		try {
			FileUtils.writeByteArrayToFile(file, filedata);

			GxwlSysDoc gxwlSysDoc = new GxwlSysDoc();
			gxwlSysDoc.setDocShowname(docShowname);
			gxwlSysDoc.setDocFullname(docFullname);
			gxwlSysDoc.setDocExtension(docExtension);
			gxwlSysDoc.setDocUri(docUri);
			gxwlSysDoc.setIsTemp(isTemp);
			gxwlSysDoc.setCreateBy(createBy);
			gxwlSysDoc.setCreateTime(new Date());
			gxwlSysDoc.setLastUpdateBy(lastUpdateBy);
			gxwlSysDoc.setLastUpdateTime(new Date());
			if (StringUtils.isNotEmpty(docExtension)) {
				gxwlSysDoc.setDocContentType(ContentType.valueOf(StringUtils.upperCase(docExtension)).toString());
			} else {
				gxwlSysDoc.setDocContentType(ContentType.valueOf("UNKNOW").toString());
			}
			doc = gxwlSysDocDao.save(gxwlSysDoc);
		} catch (Exception e) {
			e.printStackTrace();
			FileUtils.deleteQuietly(file);
			doc = new GxwlSysDoc();
			doc.setRemark(e.getMessage());
		}
		return doc;
	}

	@Override
	@Transactional
	public GxwlSysDoc moveDoc(Integer docId) {
		GxwlSysDoc gxwlSysDoc = gxwlSysDocDao.findOne(docId);
		gxwlSysDoc.setIsTemp("N");
		return gxwlSysDoc;
	}

	@Override
	@Transactional
	public List<GxwlSysDoc> moveDocList(List<Integer> ids) {
		List<GxwlSysDoc> listDoc = gxwlSysDocDao.queryListById(ids);
		if(listDoc!=null && listDoc.size()>0){
			  for(GxwlSysDoc d : listDoc){
				  d.setIsTemp("N");
			  }
			gxwlSysDocDao.save(listDoc);
		}
		return listDoc;
	}

	@Override
	public byte[] getDoc(Integer docId) {
		GxwlSysDoc gxwlSysDoc = gxwlSysDocDao.findOne(docId);
		try {
			if (gxwlSysDoc != null) {
				return FileUtils.readFileToByteArray(gxwlSysDoc.getDocFile());
			}
		} catch (IOException e) {
		}
		return new byte[0];
	}

	@Override
	@Transactional(rollbackFor = { IOException.class })
	public void deleteDoc(Integer docId) {
		GxwlSysDoc gxwlSysDoc = gxwlSysDocDao.findOne(docId);
		if (gxwlSysDoc != null) {
			FileUtils.deleteQuietly(gxwlSysDoc.getDocFile());
		}
		gxwlSysDocDao.delete(docId);
	}
	
	@Override
	@Transactional(rollbackFor = { IOException.class })
	public void deleteFile(Integer docId) {
		GxwlSysDoc gxwlSysDoc = gxwlSysDocDao.findOne(docId);
		if (gxwlSysDoc != null) {
			String filePath = BaseUtil.getSysRootPath() + gxwlSysDoc.getDocUri();
			File file = new File(filePath);
			if(file.exists()){
				file.delete();
			}
		}
		gxwlSysDocDao.delete(docId);
	}
	
	@Override
	public Map<String, Object> doSelect(Integer docId) {
		resetResultMap();
		try {
			setResultStatus(0, "查询成功");
			resultMap.put(RESULT_ROW, gxwlSysDocDao.findByDocIdAndIsTemp(docId, "N"));
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			setResultStatus(-1, "查询时系统出错: " + e.getMessage());
			return resultMap;
		}
	}
	
	@Override
	public byte[] getDocAndIsTemp(Integer docId) {
		GxwlSysDoc gxwlSysDoc = null;
		
		GxwlSysDoc docIsTemp = gxwlSysDocDao.findOne(docId);
		
		if("N".equals(docIsTemp.getIsTemp())){
			gxwlSysDoc = gxwlSysDocDao.findByDocIdAndIsTemp(docId, "N");
		}else if("Y".equals(docIsTemp.getIsTemp()) || "".equals(docIsTemp.getIsTemp())){
			gxwlSysDoc = gxwlSysDocDao.findOne(docId);
		}
		
		try {
			if (gxwlSysDoc != null) {
				return FileUtils.readFileToByteArray(gxwlSysDoc.getDocFile());
			}
		} catch (IOException e) {
		}
		return new byte[0];
	}

}
