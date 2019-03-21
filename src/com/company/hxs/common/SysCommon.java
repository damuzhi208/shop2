package com.company.hxs.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import net.sf.json.processors.JsonValueProcessor;


public final class SysCommon {
	
	public static final class SafeDateFormat {
		private ThreadLocal<DateFormat> threadLocal;

		@SuppressWarnings({ "unchecked", "rawtypes" })
		private SafeDateFormat(final String format) {
			this.threadLocal = new ThreadLocal() {
				protected synchronized DateFormat initialValue() {
					return new SimpleDateFormat(format);
				}
			};
		}

		public Date parse(String dateStr) {
			if (null == dateStr) {
				return null;
			}
			dateStr = dateStr.trim();
			if (0 == dateStr.length()) {
				return null;
			}
			try {
				return ((DateFormat) this.threadLocal.get()).parse(dateStr);
			} catch (ParseException e) {
				throw new IllegalStateException(e);
			}
		}

		public String format(Object date) {
			if (null == date) {
				return "";
			}
			return ((DateFormat) this.threadLocal.get()).format(date);
		}
	}
	
	public static final SafeDateFormat YMD = new SafeDateFormat("yyyy-MM-dd");
	public static final SafeDateFormat YMD_H = new SafeDateFormat("yyyy-MM-dd HH");
	public static final SafeDateFormat YMD_HM = new SafeDateFormat("yyyy-MM-dd HH:mm");
	public static final SafeDateFormat YMD_HMS = new SafeDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SafeDateFormat YMD_HMS2 = new SafeDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	public static final SafeDateFormat CYMD = new SafeDateFormat("yyyy-MM-dd");
	public static final SafeDateFormat CYMD_H = new SafeDateFormat("yyyy-MM-dd HH");
	public static final SafeDateFormat CYMD_HM = new SafeDateFormat("yyyy-MM-dd HH:mm");
	public static final SafeDateFormat CYMD_HMS = new SafeDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SafeDateFormat UUDT = new SafeDateFormat("yyyyMMddHHmmssSSS");
	public static final JsonValueProcessor DATE_JSON_PROCESSOR_YMD = new DateValueJsonProcessor(YMD);
	public static final JsonValueProcessor DATE_JSON_PROCESSOR_YMD_HMS = new DateValueJsonProcessor(YMD_HMS);
	public static final JsonValueProcessor DATE_JSON_PROCESSOR_YMD_HMS2 = new DateValueJsonProcessor(YMD_HMS2);
	
}
