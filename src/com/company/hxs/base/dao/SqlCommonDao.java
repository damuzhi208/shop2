package com.company.hxs.base.dao;

import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateOperations;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

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

	public <TB> List<TB> findListBySqlAsAliasToBean2(String sql, Class<TB> beanType){
		return findListBySqlAsAliasToBean2(sql, beanType, null, null, null);
	}
	
	public <TB> List<TB> findListBySqlAsAliasToBean2(String sql, Class<TB> beanType, Object[] params){
	    return findListBySqlAsAliasToBean2(sql, beanType, params, null, null);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
}
