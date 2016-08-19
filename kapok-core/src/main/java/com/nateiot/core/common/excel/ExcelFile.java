package com.nateiot.core.common.excel;

import java.io.Serializable;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Will WM. Zhang
 * 
 */
public class ExcelFile implements Serializable {

	private static final long serialVersionUID = 3929355850498418183L;

	private boolean preview;
	
	private MultipartFile file;

	private Workbook workbook;

	public boolean isPreview() {
		return preview;
	}

	public void setPreview(boolean preview) {
		this.preview = preview;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) throws Exception {
		this.file = file;
		if ("application/vnd.ms-excel".equalsIgnoreCase(file.getContentType())) {
			this.workbook = new HSSFWorkbook(file.getInputStream());
		} else {
			this.workbook = new XSSFWorkbook(file.getInputStream());
		}
	}

	public Workbook getWorkbook() {
		return workbook;
	}

}
