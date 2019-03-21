package com.company.hxs.common;

import net.sf.json.JSONNull;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class BigDecimalValueJsonProcessor implements JsonValueProcessor {

	private BigDecimalValueJsonProcessor() {
		
	}

	public Object processArrayValue(Object value, JsonConfig jc) {
		return processObjectValue(null, value, jc);
	}

	public Object processObjectValue(String propName, Object value, JsonConfig jc) {
		if (null == value) {
			return JSONNull.getInstance();
		}
		return value;
	}
	
}
