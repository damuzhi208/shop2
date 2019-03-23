package com.company.hxs.common.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;

import com.company.hxs.base.dao.ISqlCommonDao;

public abstract interface IBaseDao<E, PK extends Serializable> extends ISqlCommonDao {
	
	public abstract HibernateTemplate getHibernateTemplate();

	public abstract String getTableName();

	public abstract String getSimpleName();

	public abstract String getCanonicalName();

	public abstract E get(PK paramPK);

	public abstract void remove(E paramE);

	public abstract int findCountByHql(String paramString, Object[] paramArrayOfObject);

	@Deprecated
	public abstract Object sqlFindUnique(String paramString, Object... paramVarArgs);

	@Deprecated
	public abstract <T> T sqlFindUnique(String paramString, Class<T> paramClass, Object... paramVarArgs);

	@Deprecated
	public abstract Integer sqlGetCount(String paramString, Object... paramVarArgs);

	@Deprecated
	public abstract Integer getCount(String paramString, Object... paramVarArgs);

	public List<E> findListToAliasToBeanBySql(final String sql);
	
	public List<E> findListToAliasToBeanBySql(final String sql,final Object[] params);
	
	public List<E> findListToAliasToBeanBySql(final String sql, final Integer page,final Integer size);
	
	public List<E> findListToAliasToBeanBySql(final String sql,final Object[] params, final Integer page,final Integer size);
	
}