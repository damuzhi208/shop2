package com.company.hxs.base.dao;

import org.springframework.orm.hibernate4.HibernateOperations;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.io.Serializable;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;
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

}
