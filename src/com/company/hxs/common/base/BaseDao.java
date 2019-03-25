package com.company.hxs.common.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.company.hxs.base.dao.SqlCommonDao;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseDao<E, PK extends Serializable> extends SqlCommonDao implements IBaseDao<E, PK> {
//	
//	@Resource
//	protected HibernateTemplate hibernateTemplate;
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

	public int findCountByHql(final String hql, final Object[] params) {
		return this.hibernateTemplate.find(hql, params).size();
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
	    return getHibernateTemplate().execute(new HibernateCallback() {
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
				BaseDao.this.logger.debug(c);
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
	 * sql查询返回唯一结果
	 */
	public Object sqlFindUnique(String sql, Object... params) {
		List<?> list = sqlFind(sql, params);
		if ((list == null) || (list.size() == 0)) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * sql查询返回唯一对象
	 */
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

	/**
	 * 强制转换返回类型
	 * @param sql
	 * @param params
	 * @return
	 */
	public <T> T getUniqueBySqlAsAutoCast(final String sql, final Object[] params) {
		return (T) this.hibernateTemplate.execute(new HibernateCallback() {
			public T doInHibernate(Session session) throws HibernateException {
				SQLQuery q = session.createSQLQuery(sql);
				if (null != params) {
					for (int i = 0; i < params.length; i++) {
						q.setParameter(i, params[i]);
					}
				}
				return (T) q.uniqueResult();
			}
		});
	}
	
	public List<E> findByHql(String hql, Object[] values){
		return (List<E>) this.getHibernateTemplate().find(hql, values);
	}
	
	/**
	 * hql返回list对象
	 * @param hql
	 * @return
	 */
	public List<E> findByHql(final String hql){
		return findByHql(hql, null);
	}
	
	/**
	 * hql查询分页
	 * @param hql
	 * @param params
	 * @param page
	 * @param size
	 * @return
	 */
	public List<E> findByHql(final String hql, final Object[] params, final Integer page, final Integer size) {
		return (List<E>) this.getHibernateTemplate().execute(new HibernateCallback() {
			public List<E> doInHibernate(Session session) {
				Query q = session.createQuery(hql);
				if (null != params) {
					for (int i = 0; i < params.length; i++) {
						q.setParameter(i, params[i]);
					}
				}
				if(page != null && size != null){
					q.setFirstResult((page - 1)*size);
					q.setMaxResults(size);
				}
				return q.list();
			}
		});
	}
	
	/**
	 * 返回唯一对象
	 * @param sql
	 * @return
	 */
	public E findUniqueBeanBySql(final String sql){
		return findUniqueBeanBySql(sql, null);
	}
	
	/**
	 * 返回唯一对象
	 * @param sql
	 * @param params
	 * @return
	 */
	public E findUniqueBeanBySql(final String sql,final Object[] params){
		return (E) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
            public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				if(params != null){
					for(int i = 0; i < params.length; i++){
						query.setParameter(i, params[i]);
					}
				}
				query.setResultTransformer(Transformers.aliasToBean(type));
                return (E)query.uniqueResult();  
            }  
       });
	}
	
	/**
	 * sql查询返回list集合
	 * @param sql
	 * @return
	 */
	public List<E> findListToAliasToBeanBySql(final String sql){
		return findListToAliasToBeanBySql(sql, null);
	}
	
	/**
	 * sql查询返回list集合
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<E> findListToAliasToBeanBySql(final String sql,final Object[] params){
		return findListToAliasToBeanBySql(sql, params, null, null);
	}
	
	/**
	 * sql查询返回list集合
	 * @param sql
	 * @param page
	 * @param size
	 * @return
	 */
	public List<E> findListToAliasToBeanBySql(final String sql, final Integer page,final Integer size){
		return findListToAliasToBeanBySql(sql, null, page, size);
	}
	
	/**
	 * sql查询返回list集合
	 * @param sql
	 * @param params
	 * @param page
	 * @param size
	 * @return
	 */
	public List<E> findListToAliasToBeanBySql(final String sql,final Object[] params, final Integer page,final Integer size){
		return (List<E>) getHibernateTemplate().execute(new HibernateCallback() {
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
				query.setResultTransformer(Transformers.aliasToBean(type));
                return (List<E>)query.list();  
            }  
       });
	}
	
}
