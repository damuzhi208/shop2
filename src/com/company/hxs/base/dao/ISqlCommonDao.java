package com.company.hxs.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate4.HibernateOperations;

import com.company.hxs.common.PageInfo;

public abstract interface ISqlCommonDao extends HibernateOperations {
	
	public abstract <G> G getEntity(Class<G> paramClass, Serializable paramSerializable);

	public abstract <G> void persistEntity(G paramG);

	public abstract <G> G mergeEntity(G paramG);

	public abstract <G> void removeEntity(G paramG);

	public abstract <G> List<G> findEntityListBySql(Class<G> paramClass, String paramString, Object[] paramArrayOfObject, int paramInt1, int paramInt2);

	public abstract <G> List<G> findEntityListBySql(Class<G> paramClass, String paramString, Object[] paramArrayOfObject, PageInfo paramPageInfo);

	public abstract <G> List<G> findEntityListBySql(Class<G> paramClass, String paramString, Object[] paramArrayOfObject);

	public abstract <G> List<G> findEntityListBySql(Class<G> paramClass, String paramString);

	public abstract int executeSQL(String paramString);

	public abstract int executeSQL(String paramString, Object[] paramArrayOfObject);

	public abstract int findCountBySql(String paramString, Object[] paramArrayOfObject);

	@SuppressWarnings("rawtypes")
	public abstract List findBySql(String paramString, Object[] paramArrayOfObject);

	public abstract int getCountBySql(String paramString1, String paramString2, Object[] paramArrayOfObject);

	public abstract <TB> TB getUniqueBySqlAsAliasToBean(String paramString, Class<TB> paramClass, Object[] paramArrayOfObject);

	public abstract <TB> TB getUniqueBySqlAsAliasToBean(String paramString, Class<TB> paramClass);

	@Deprecated
	public abstract <TB> List<TB> findListBySqlAsAliasToBean( String paramString, Class<TB> paramClass, Object[] paramArrayOfObject, PageInfo paramPageInfo);

	@Deprecated
	public abstract <TB> List<TB> findListBySqlAsAliasToBean(String paramString, Class<TB> paramClass, Object[] paramArrayOfObject, Integer paramInteger1, Integer paramInteger2);

	@Deprecated
	public abstract <TB> List<TB> findListBySqlAsAliasToBean(String paramString, Class<TB> paramClass, Object[] paramArrayOfObject);

	@Deprecated
	public abstract <TB> List<TB> findListBySqlAsAliasToBean(String paramString, Class<TB> paramClass);

	public abstract <TB> TB getUniqueBySqlAsAliasToBean2(String paramString, Class<TB> paramClass, Object[] paramArrayOfObject);

	public abstract <TB> TB getUniqueBySqlAsAliasToBean2(String paramString, Class<TB> paramClass);

	public abstract <TB> List<TB> findListBySqlAsAliasToBean2(String paramString, Class<TB> paramClass, Object[] paramArrayOfObject, Integer paramInteger1, Integer paramInteger2);

	public abstract <TB> List<TB> findListBySqlAsAliasToBean2(String paramString, Class<TB> paramClass, Object[] paramArrayOfObject);

	public abstract <TB> List<TB> findListBySqlAsAliasToBean2(String paramString, Class<TB> paramClass);

	public abstract <AC> AC getUniqueBySqlAsAutoCast(String paramString, Object[] paramArrayOfObject);

	public abstract <AC> AC getUniqueBySqlAsAutoCast(String paramString);

	public abstract <AC> List<AC> findListBySqlAsAutoCast(String paramString, Object[] paramArrayOfObject, Integer paramInteger1, Integer paramInteger2);

	public abstract <AC> List<AC> findListBySqlAsAutoCast(String paramString, Object[] paramArrayOfObject);

	public abstract <AC> List<AC> findListBySqlAsAutoCast(String paramString);

	public abstract Integer sqlGetCount(String paramString, Object... paramVarArgs);

	public abstract <E> List<E> sqlFindPage(String paramString, Class<E> paramClass, Integer paramInteger1, Integer paramInteger2, Object... paramVarArgs);

	public abstract <E> List<E> sqlFind(String paramString, Object... paramVarArgs);

	public abstract <E> List<E> sqlFind(String paramString, Class<E> paramClass, Object... paramVarArgs);

	public abstract <E> E sqlFindUnique(String paramString, Class<E> paramClass, Object... paramVarArgs);

	public abstract List<Map<String, Object>> findListBySqlAsMap(String paramString, Object[] paramArrayOfObject);

	public abstract List<Map<String, Object>> findListBySqlAsMap(String paramString, Object[] paramArrayOfObject, Integer paramInteger1, Integer paramInteger2);

	public abstract Map<String, Object> getUniqueBySqlAsMap(String paramString, Object[] paramArrayOfObject);

	public abstract Map<String, Object> getUniqueBySqlAsMap(String paramString);

	public abstract List<String> exportInsertSql(String paramString1, String paramString2);
}
