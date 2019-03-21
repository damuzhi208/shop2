package com.company.hxs.common;

import java.sql.Timestamp;

import net.sf.json.JsonConfig;

public class JSONConfigFactory {
	
	public static JsonConfig getSimpleConfig() {
		JsonConfig jc = new JsonConfig();
		jc.registerJsonValueProcessor(java.util.Date.class, SysCommon.DATE_JSON_PROCESSOR_YMD_HMS);
		jc.registerJsonValueProcessor(java.sql.Date.class, SysCommon.DATE_JSON_PROCESSOR_YMD);
		jc.registerJsonValueProcessor(Timestamp.class, SysCommon.DATE_JSON_PROCESSOR_YMD_HMS);
		return jc;
	}

	public static JsonConfig getYMDConfig() {
		JsonConfig jc = new JsonConfig();
		jc.registerJsonValueProcessor(java.util.Date.class, SysCommon.DATE_JSON_PROCESSOR_YMD);
		jc.registerJsonValueProcessor(java.sql.Date.class, SysCommon.DATE_JSON_PROCESSOR_YMD);
		jc.registerJsonValueProcessor(Timestamp.class, SysCommon.DATE_JSON_PROCESSOR_YMD);
		return jc;
	}

	public static JsonConfig getYMDHMSConfig() {
		JsonConfig jc = new JsonConfig();
		jc.registerJsonValueProcessor(java.util.Date.class, SysCommon.DATE_JSON_PROCESSOR_YMD_HMS);
		jc.registerJsonValueProcessor(java.sql.Date.class, SysCommon.DATE_JSON_PROCESSOR_YMD_HMS);
		jc.registerJsonValueProcessor(Timestamp.class, SysCommon.DATE_JSON_PROCESSOR_YMD_HMS);
		return jc;
	}
}
