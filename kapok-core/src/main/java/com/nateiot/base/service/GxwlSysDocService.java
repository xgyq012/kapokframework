package com.nateiot.base.service;

import org.springframework.web.multipart.MultipartFile;

import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.base.repository.GxwlSysDocDao;
import com.nateiot.core.service.BaseService;

import java.util.List;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysDocService extends
		BaseService<GxwlSysDocDao, GxwlSysDoc, Integer> {

	public GxwlSysDoc saveDoc(MultipartFile file, String directory);
	
	public GxwlSysDoc saveDoc(byte[] filedata, String directory, Integer userId);

	public GxwlSysDoc saveDoc(byte[] filedata, String directory, String originalFilename, String isTemp, Integer userId);
	
	public GxwlSysDoc moveDoc(Integer docId);
	
	public byte[] getDoc(Integer docId);
	
	public void deleteDoc(Integer docId);
	
	public byte[] getDocAndIsTemp(Integer docId);
	
	public void deleteFile(Integer docId);

	public List<GxwlSysDoc> moveDocList(List<Integer> ids);

}
