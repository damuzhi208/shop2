package com.company.hxs.common.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.company.hxs.base.dao.SqlCommonDao;
import com.company.hxs.common.IBaseService;
import com.company.hxs.common.JSONConfigFactory;

public class BaseService implements IBaseService{

	@Resource protected SqlCommonDao sqlCommonDao;
	
	public static String List2Json(List<?> result, Integer total) {
		JSONArray array = JSONArray.fromObject(result,JSONConfigFactory.getYMDConfig());
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", array);
		return json.toString();
	}

	public static String List2Json4FullDate(List<?> result, Integer total) {
		JSONArray array = JSONArray.fromObject(result, JSONConfigFactory.getYMDHMSConfig());
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", array);
		return json.toString();
	}
	
	public static String List2Json4FullDate(List<?> result, List<?> footer, Integer total) {
		JSONArray array = JSONArray.fromObject(result, JSONConfigFactory.getYMDHMSConfig());
		JSONArray footerArray = JSONArray.fromObject(footer, JSONConfigFactory.getYMDHMSConfig());
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("footer", footerArray);
		json.put("rows", array);
		return json.toString();
	}

	public static JSONObject List2JsonObject(List<?> result, Integer total) {
		JSONArray array = JSONArray.fromObject(result, JSONConfigFactory.getYMDConfig());
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", array);
		return json;
	}

	public static JSONObject List2JsonObject(List<?> result, Integer total, List<?> footer) {
		JSONArray array = JSONArray.fromObject(result, JSONConfigFactory.getYMDConfig());
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", array);
		json.put("footer", JSONArray.fromObject(footer, JSONConfigFactory.getYMDConfig()));
		return json;
	}

	public static JSONObject List2JsonObjectLongDate(List<?> result, Integer total) {
		JSONArray array = JSONArray.fromObject(result, JSONConfigFactory.getSimpleConfig());
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", array);
		return json;
	}

	public static JSONObject List2JsonObjectLongDate(List<?> result, Integer total, List<?> footer) {
		JSONArray array = JSONArray.fromObject(result, JSONConfigFactory.getSimpleConfig());
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", array);
		json.put("footer", footer);
		return json;
	}

	public static String List2Json(List<?> result, Integer total, List<?> footer) {
		JSONArray array = JSONArray.fromObject(result, JSONConfigFactory.getYMDConfig());
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", array);
		json.put("footer", footer);
		return json.toString();
	}

	public static String List2JsonLongDate(List<?> result, Integer total) {
		JSONArray array = JSONArray.fromObject(result, JSONConfigFactory.getSimpleConfig());
		JSONObject json = new JSONObject();
		json.put("total", total);
		json.put("rows", array);
		return json.toString();
	}

	public static JSONArray list2JsonArray(List<?> list) {
		if (list == null) {
			return new JSONArray();
		}
		JSONArray array = JSONArray.fromObject(list, JSONConfigFactory.getYMDConfig());
		return array;
	}

	public static JSONArray list2JsonArrayLongDate(List<?> list) {
		if (list == null) {
			return new JSONArray();
		}
		JSONArray array = JSONArray.fromObject(list, JSONConfigFactory.getSimpleConfig());
		return array;
	}

	@Override
	public HttpServletRequest getCurRequest() {
		return null;
	}

	@Override
	public <E> E getObject(Class<E> entityClass, Serializable id) {
		if (id == null) {
			return null;
		}
		return (E) this.sqlCommonDao.getEntity(entityClass, id);
	}

	@Override
	public boolean deleteObject(Object obj) {
		try {
			this.sqlCommonDao.removeEntity(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(Object obj) {
		try {
			this.sqlCommonDao.saveOrUpdate(obj);
			return true;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return false;
	}

	@Override
	public <E> String jsonGet(Class<E> entityClass, Serializable id) {
		E obj = getObject(entityClass, id);
	    return JSONObject.fromObject(obj).toString();
	}

	@Override
	public String jsonDelete(Object obj) {
		JSONObject json = new JSONObject();
	    Boolean result = Boolean.valueOf(deleteObject(obj));
	    json.put("success", result);
	    if (result.booleanValue()) {
	    	json.put("msg", "É¾³ý³É¹¦!");
	    } else if (!result.booleanValue()) {
	    	json.put("msg", "É¾³ýÊ§°Ü!");
	    }
	    return json.toString();
	}

	@Override
	public String jsonUpdate(Object obj) {
		JSONObject json = new JSONObject();
		boolean result = saveOrUpdate(obj);
		if (result) {
			json.put("success", Boolean.valueOf(true));
			json.put("msg", "²Ù×÷³É¹¦!");
		} else {
			json.put("success", Boolean.valueOf(false));
			json.put("msg", "²Ù×÷Ê§°Ü!");
		}
		return json.toString();
	}

	@Override
	public String msg2json(Boolean success, String msg) {
		JSONObject json = new JSONObject();
	    json.put("success", success);
	    json.put("msg", msg);
	    return json.toString();
	}

	@Override
	public <E> JSONObject jsonSaveOrUpdate(Class<E> paramClass, Object o) {
		return null;
	}

}
