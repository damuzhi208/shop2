package com.company.hxs.base.dao;

import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateOperations;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.company.hxs.common.Page;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
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

	public <TB> List<TB> findListBySqlAsAliasToBean2(String sql, Class<TB> beanType){
		return findListBySqlAsAliasToBean2(sql, beanType, null, null, null);
	}
	
	public <TB> List<TB> findListBySqlAsAliasToBean2(String sql, Class<TB> beanType, Object[] params){
	    return findListBySqlAsAliasToBean2(sql, beanType, params, null, null);
	}
	
	public <TB> List<TB> findListBySqlAsAliasToBean2(final String sql, final Class<TB> beanType, final Object[] params, final Integer page, final Integer size) {
		return (List<TB>) this.hibernateTemplate.execute(new HibernateCallback() {
			@Override  
            public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				if(params != null){
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				if(page != null && size != null){
					query.setFirstResult((page - 1)*size);
					query.setMaxResults(size);
				}
				query.setResultTransformer(Transformers.aliasToBean(beanType));
                return (List<TB>)query.list();  
            }  
       });
	}
	
	public Integer sqlGetCount(String sql, Object... params) {
		List result = sqlFind(sql, params);
		if ((!result.isEmpty()) && (result.get(0) != null)) {
			this.logger.debug("result[0]:" + result.get(0));
			return Integer.valueOf(result.get(0).toString());
		}
		this.logger.warn("result error!");

		return Integer.valueOf(0);
	}
	
	public List<?> sqlFind(String sql, Object... params) {
		return sqlFind(sql, null, null, null, params);
	}

	public <T> List<T> sqlFind(String sql, Class<T> c, Object... params) {
		return sqlFind(sql, c, null, null, params);
	}

	public <T> List<T> sqlFind(final String sql, final Class<T> c, final Integer page, final Integer pageSize, final Object... params) {
	    this.logger.debug("sql:" + sql);
	    this.logger.debug("page:" + page);
	    this.logger.debug("pageSize:" + pageSize);
	    this.logger.debug("params:" + params);
	    return this.hibernateTemplate.execute(new HibernateCallback() {
	    	public Object doInHibernate(Session session) throws HibernateException {
	        SQLQuery query = session.createSQLQuery(sql);
			if (null != params) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
	        if ((page != null) && (pageSize != null) && (page.intValue() > 0) && (pageSize.intValue() > 0)) {
				query.setFirstResult((page.intValue() - 1) * pageSize.intValue());
				query.setMaxResults(pageSize.intValue());
	        }
	        if (c != null) {
				if (c.isAssignableFrom(Map.class)) {
					query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
				} else {
					query.setResultTransformer(Transformers.aliasToBean(c));
				}
	        }
	        return query.list();
	      }
	    });
	}
	
	/**
	 * sql查询返回分页bean
	 * @param sql
	 * @param c
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public <TB> Page<TB> sqlFindPage(final String sql, final Class<TB> c, Integer pageNumber, Integer pageSize){
		return this.sqlFindPage(sql, c, null, pageNumber, pageSize);
	}
	
	/**
	 * sql查询返回分页bean
	 * @param sql
	 * @param c
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public <TB> Page<TB> sqlFindPage(final String sql, final Class<TB> c,final Object[] params, Integer pageNumber, Integer pageSize){
		StringBuffer sb = new StringBuffer(sql);
		int total = sqlGetCount("select count(*) from ("+sb.toString()+") o", params);
		if (pageNumber == null || pageNumber < 1){
			pageNumber = 1;
		}
		if (pageSize == null || pageSize < 1){
			pageSize = 10;
		}
		if (total < 1) {
			return new Page(new ArrayList<>(), pageNumber, pageSize, 0);
		}
		final int start = (pageNumber - 1) * pageSize;
		final int count = start + pageSize;
		List<TB> list = (List<TB>) this.hibernateTemplate.execute(new HibernateCallback() {
			public List<Map<String, Object>> doInHibernate(final Session session) {
				Query query = session.createSQLQuery(sql);
				query.setFirstResult(start);
				query.setMaxResults(count);
				if (null != params) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				query.setResultTransformer(Transformers.aliasToBean(c));
				return query.list();
			}
		});
		Page page = new Page(list, pageNumber, pageSize, total);
		return page;
	}
	
}
