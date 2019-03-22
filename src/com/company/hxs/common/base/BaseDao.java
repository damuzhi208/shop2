package com.company.hxs.common.base;

import java.io.Serializable;
import java.util.List;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.company.hxs.base.dao.SqlCommonDao;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseDao<E, PK extends Serializable> extends SqlCommonDao implements IBaseDao<E, PK> {
	
	@Resource
	protected HibernateTemplate hibernateTemplate;
	protected Class<E> type;

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	private JdbcDaoSupport jdbcDao = null;

	public void setJdbcDao(JdbcDaoSupport jdbcDao) {
		this.jdbcDao = jdbcDao; 
	}

	public JdbcDaoSupport getJdbcDao() {
		return this.jdbcDao;
	}
	
	public Object saveOrUpdateEntity(Object obj) {
		try {
			getHibernateTemplate().saveOrUpdate(obj);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.error(e.getMessage());
		}
		return null;
	}

	public BaseDao() {
		Class c = getClass();
		Type t = c.getGenericSuperclass();
		if ((t instanceof ParameterizedType)) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.type = ((Class) p[0]);
		}
	}

	public boolean deleteEntity(Object obj) {
		try {
			if (obj == null) {
				return false;
			}
			getHibernateTemplate().delete(obj);
			return true;
		} catch (RuntimeException re) {
			re.printStackTrace();
			this.logger.error(re.getMessage());
		}
		return false;
	}

	public boolean deleteAllEntity(List<?> list) {
		try {
			if (list == null) {
				return false;
			}
			getHibernateTemplate().deleteAll(list);
			return true;
		} catch (RuntimeException re) {
			re.printStackTrace();
			this.logger.error(re.getMessage());
		}
		return false;
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory1(SessionFactory sessionFactory) {
		this.logger.debug(sessionFactory);
		super.setSessionFactory(sessionFactory);
	}

	public String getTableName() {
		AbstractEntityPersister persister = (AbstractEntityPersister) getHibernateTemplate()
				.getSessionFactory().getClassMetadata(this.type);
		return persister.getTableName();
	}

	public String getSimpleName() {
		return this.type.getSimpleName();
	}

	public String getCanonicalName() {
		return this.type.getCanonicalName();
	}

	public E get(PK id) {
		if (id == null) {
			return null;
		}
		return (E) this.hibernateTemplate.get(this.type, id);
	}

	public <T> T getEntity(Class<T> entityClass, Serializable id) {
		return (T) this.hibernateTemplate.get(entityClass, id);
	}

	public <T> T mergeEntity(T entity) {
		return (T) this.hibernateTemplate.merge(entity);
	}

	public <T> void persistEntity(T entity) {
		this.hibernateTemplate.persist(entity);
	}

	public void remove(E entity) {
		this.hibernateTemplate.delete(entity);
	}

	public <T> void removeEntity(T entity) {
		this.hibernateTemplate.delete(entity);
	}

	public int findCountByHql(String hql, Object[] params) {
		return ((Long) this.hibernateTemplate.find(hql, params).get(0))
				.intValue();
	}

	public List<?> sqlFind(String sql, Object... params) {
		return sqlFind(sql, null, null, null, params);
	}

	public <T> List<T> sqlFind(String sql, Class<T> c, Object... params) {
		return sqlFind(sql, c, null, null, params);
	}

	public Object sqlFindUnique(String sql, Object... params) {
		List<?> list = sqlFind(sql, params);
		if ((list == null) || (list.size() == 0)) {
			return null;
		}
		return list.get(0);
	}

	public <T> T sqlFindUnique(String sql, Class<T> c, Object... params) {
		List<?> list = sqlFind(sql, c, params);
		if ((list == null) || (list.size() == 0)) {
			return null;
		}
		return (T) list.get(0);
	}

	public int excuteSQL(String sql) {
		try {
			return getHibernateTemplate().bulkUpdate(sql);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
	}

	public E findById(Class<E> model, PK id) {
		try {
			return (E) getHibernateTemplate().get(model, id);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
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

	public Integer getCount(String hql, Object... value) {
		try {
			List<?> result = getHibernateTemplate().find(hql, value);
			return Integer.valueOf(result.get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
			this.logger.error("hql==" + hql);
			this.logger.error(e.getMessage(), e);
		}
		return Integer.valueOf(0);
	}

	public <T> List<T> getBeanListBysql(String sql, Class<T> c, Object... params) {
		return getBeanListBysql(sql, c, null, null, params);
	}
	
	public <T> List<T> findByHql(String hql){
		return (List<T>) this.getHibernateTemplate().find(hql);
	}
	
	public <T> List<T> findByHql(String hql, Object[] values){
		return (List<T>) this.getHibernateTemplate().find(hql, values);
	}
	
	public <T> List<T> findListToAliasToBeanBySql(final String sql){
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				return (T) query.list();
			}
		});
	}
	
	public <T> List<T> findListToAliasToBeanBySql(final String sql,Object[] params){
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		query.setResultTransformer(Transformers.aliasToBean(type));
		return query.list();
	}
	
	public <T> List<T> findListToAliasToBeanBySql(final String sql, final Integer page,final Integer size){
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				query.setMaxResults(size);
				query.setFirstResult(page);
				return (T) query.list();
			}
		});
	}
	
	public <T> List<T> findListToAliasToBeanBySql(final String sql, Object[] params, final Integer page,final Integer size){
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		query.setMaxResults(size);
		query.setFirstResult(page);
		query.setResultTransformer(Transformers.aliasToBean(type));
		return query.list();
	}
	
}
