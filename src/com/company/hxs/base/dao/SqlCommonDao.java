package com.company.hxs.base.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateOperations;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.company.hxs.common.PageInfo;

@Repository
public class SqlCommonDao extends HibernateTemplate implements ISqlCommonDao, HibernateOperations{
	
	@Resource
	protected HibernateTemplate hibernateTemplate;

	@Resource(name = "sessionFactory")
	public void setSessionFactory1(SessionFactory sessionFactory) {
		this.logger.debug(sessionFactory);
		super.setSessionFactory(sessionFactory);
	}

	public <T> T getEntity(Class<T> entityClass, Serializable id) {
		if (id == null) {
			return null;
		}
		return (T) this.hibernateTemplate.get(entityClass, id);
	}

	public <T> T mergeEntity(T entity) {
		return (T) this.hibernateTemplate.merge(entity);
	}

	public <T> void persistEntity(T entity) {
		this.hibernateTemplate.persist(entity);
	}

	public <T> void removeEntity(T entity) {
		this.hibernateTemplate.delete(entity);
	}

	public int getCountBySql(String countName, String fromSql, Object[] params) {
		String sql = String.format("select count(%1$s) ", new Object[] { countName }) + fromSql;
		return ((Integer) getUniqueBySqlAsAutoCast(sql, params)).intValue();
	}

	public int executeSQL(final String sql) {
		/*Integer result = (Integer) this.hibernateTemplate
				.execute(new HibernateCallback() {
					public Integer doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						return Integer.valueOf(query.executeUpdate());
					}
				});
		return result.intValue();*/
		return 0;
	}

	public int executeSQL(final String sql, final Object[] params) {
		/*Integer result = (Integer) this.hibernateTemplate
				.execute(new HibernateCallback() {
					public Integer doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						if (null != params) {
							for (int i = 0; i < params.length; i++) {
								query.setParameter(i, params[i]);
							}
						}
						return Integer.valueOf(query.executeUpdate());
					}
				});
		return result.intValue();*/
		return 0;
	}

	public <T> T getUniqueBySqlAsAliasToBean(String sql, Class<T> beanType) {
		return (T) getUniqueBySqlAsAliasToBean(sql, beanType, null);
	}

	public <T> List<T> findListBySqlAsAliasToBean(String sql,
			Class<T> beanType, Object[] params, PageInfo page) {
		if (null == page) {
			return findListBySqlAsAliasToBean(sql, beanType, params, null, null);
		}
		return findListBySqlAsAliasToBean(sql, beanType, params,
				page.getCurrentPage(), page.getMaxResult());
	}

	public <T> List<T> findListBySqlAsAliasToBean(String sql,
			Class<T> beanType, Object[] params) {
		return findListBySqlAsAliasToBean(sql, beanType, params, null);
	}

	public <T> List<T> findListBySqlAsAliasToBean(String sql, Class<T> beanType) {
		return findListBySqlAsAliasToBean(sql, beanType, null, null);
	}

	public <T> T getUniqueBySqlAsAutoCast(String sql) {
		return (T) getUniqueBySqlAsAutoCast(sql, null);
	}

	public <T> List<T> findListBySqlAsAutoCast(final String sql,
			final Object[] params, final Integer curPage, final Integer pageSize) {
		/*Object obj = this.hibernateTemplate
				.executeFind(new HibernateCallback() {
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery q = session.createSQLQuery(sql);
						if (null != params) {
							for (int i = 0; i < params.length; i++) {
								q.setParameter(i, params[i]);
							}
						}
						if ((null != curPage) && (null != pageSize)) {
							q.setFirstResult((curPage.intValue() - 1)
									* pageSize.intValue());
							q.setMaxResults(pageSize.intValue());
						}
						return q.list();
					}
				});
		return (List) obj;*/
		return null;
	}

	public <T> List<T> findListBySqlAsAutoCast(String sql, Object[] params) {
		return findListBySqlAsAutoCast(sql, params, null, null);
	}

	public <T> List<T> findListBySqlAsAutoCast(String sql) {
		return findListBySqlAsAutoCast(sql, null);
	}

	public <G> List<G> findEntityListBySql(Class<G> entityClass, String sql,
			Object[] params) {
		return findEntityListBySql(entityClass, sql, params, null);
	}

	public <G> List<G> findEntityListBySql(Class<G> entityClass, String sql) {
		return findEntityListBySql(entityClass, sql, null, null);
	}

	public <TB> TB getUniqueBySqlAsAliasToBean2(String sql, Class<TB> beanType) {
		return (TB) getUniqueBySqlAsAliasToBean2(sql, beanType, null);
	}


	public <TB> List<TB> findListBySqlAsAliasToBean2(String sql,
			Class<TB> beanType, Object[] params) {
		return findListBySqlAsAliasToBean2(sql, beanType, params, null, null);
	}

	public <TB> List<TB> findListBySqlAsAliasToBean2(String sql,
			Class<TB> beanType) {
		return findListBySqlAsAliasToBean2(sql, beanType, null, null, null);
	}


	public <E> List<E> sqlFind(String sql, Object... params) {
		return sqlFindPage(sql, null, null, null, params);
	}

	public <E> List<E> sqlFind(String sql, Class<E> c, Object... params) {
		return sqlFindPage(sql, c, null, null, params);
	}

	public <E> E sqlFindUnique(String sql, Class<E> c, Object... params) {
		List<E> list = sqlFind(sql, c, params);
		if (list.isEmpty()) {
			return null;
		}
		if (list.size() > 1) {
			this.logger.warn("查询结果不唯一,sql=" + sql);
		}
		return (E) list.get(0);
	}

	public Integer sqlGetCount(String sql, Object... params) {
		List<Integer> result = sqlFind(sql, params);
		if ((!result.isEmpty()) && (result.get(0) != null)) {
			this.logger.debug("result[0]:" + result.get(0));
			return Integer.valueOf(((Integer) result.get(0)).toString());
		}
		this.logger.warn("result异常!");

		return Integer.valueOf(0);
	}

	@Override
	public <G> List<G> findEntityListBySql(Class<G> paramClass,
			String paramString, Object[] paramArrayOfObject, int paramInt1,
			int paramInt2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <G> List<G> findEntityListBySql(Class<G> paramClass,
			String paramString, Object[] paramArrayOfObject,
			PageInfo paramPageInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findCountBySql(String paramString, Object[] paramArrayOfObject) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List findBySql(String paramString, Object[] paramArrayOfObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TB> TB getUniqueBySqlAsAliasToBean(String paramString,
			Class<TB> paramClass, Object[] paramArrayOfObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TB> List<TB> findListBySqlAsAliasToBean(String paramString,
			Class<TB> paramClass, Object[] paramArrayOfObject,
			Integer paramInteger1, Integer paramInteger2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TB> TB getUniqueBySqlAsAliasToBean2(String paramString,
			Class<TB> paramClass, Object[] paramArrayOfObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TB> List<TB> findListBySqlAsAliasToBean2(String paramString,
			Class<TB> paramClass, Object[] paramArrayOfObject,
			Integer paramInteger1, Integer paramInteger2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <AC> AC getUniqueBySqlAsAutoCast(String paramString,
			Object[] paramArrayOfObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> sqlFindPage(String paramString, Class<E> paramClass,
			Integer paramInteger1, Integer paramInteger2,
			Object... paramVarArgs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findListBySqlAsMap(String paramString,
			Object[] paramArrayOfObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findListBySqlAsMap(String paramString,
			Object[] paramArrayOfObject, Integer paramInteger1,
			Integer paramInteger2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getUniqueBySqlAsMap(String paramString,
			Object[] paramArrayOfObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getUniqueBySqlAsMap(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> exportInsertSql(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return null;
	}

}
