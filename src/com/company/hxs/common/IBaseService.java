package com.company.hxs.common;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONObject;

public abstract interface IBaseService {
	
	public abstract HttpServletRequest getCurRequest();

	public abstract <E> E getObject(Class<E> paramClass, Serializable paramSerializable);

	public abstract boolean deleteObject(Object paramObject);

	public abstract boolean saveOrUpdate(Object paramObject);

	public abstract <E> String jsonGet(Class<E> paramClass, Serializable paramSerializable);

	public abstract String jsonDelete(Object paramObject);

	public abstract String jsonUpdate(Object paramObject);

	public abstract String msg2json(Boolean paramBoolean, String paramString);

	public abstract <E> JSONObject jsonSaveOrUpdate(Class<E> paramClass, Object paramObject);

}
