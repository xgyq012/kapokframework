package com.gdgxwl.core.repository.impl;

import com.gdgxwl.core.repository.impl.Field.DataType;

import java.util.ArrayList;
import java.util.List;

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
