package com.nateiot.core.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.nateiot.core.repository.impl.Field.DataType;

/**
 * @author Will WM. Zhang
 *
 */
public class ResultFields {
	
	private List<Field> fields;
	
	public ResultFields() {
		this.fields = new ArrayList<Field>();
	}
	
	public ResultFields addField(String property) {
		this.fields.add(new Field(property));
		return this;
	}

	public ResultFields addField(String property, DataType dataType) {
		this.fields.add(new Field(property, dataType));
		return this;
	}
	
	public ResultFields addField(String property, DataType dataType, String dataFormat) {
		this.fields.add(new Field(property, dataType, dataFormat));
		return this;
	}

	public List<Field> getFields() {
		return fields;
	}
	
}
