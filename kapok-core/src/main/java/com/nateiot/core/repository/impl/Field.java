package com.nateiot.core.repository.impl;

/**
 * @author Will WM. Zhang
 * 
 */
public class Field {

	private String property;

	private DataType dataType;
	
	private String dataFormat;

	public enum DataType {
		String, Date, Timestamp;
	}
	
	public Field(String property) {
		this.property = property;
		this.dataType = DataType.String;
	}

	public Field(String property, DataType dataType) {
		this.property = property;
		this.dataType = dataType;
	}

	public Field(String property, DataType dataType, String dataFormat) {
		this.property = property;
		this.dataType = dataType;
		this.dataFormat = dataFormat;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public String getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

}