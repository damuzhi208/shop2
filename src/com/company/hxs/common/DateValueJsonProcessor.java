package com.company.hxs.common;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateValueJsonProcessor implements JsonValueProcessor {
	
	private SysCommon.SafeDateFormat sdf;

	public DateValueJsonProcessor(SysCommon.SafeDateFormat sdf) {
		this.sdf = sdf;
	}

	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return process(arg0);
	}

	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		return process(arg1);
	}

	public String process(Object date) {
		if (null == date) {
			return "";
		}
		return this.sdf.format(date);
	}
	
}