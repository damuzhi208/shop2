package com.company.hxs.common.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mysql.jdbc.Statement;

/**
 * 扩展Spring里JdbcDaoSupport提供更多功能
 */
@SuppressWarnings({"rawtypes","unchecked"})
public abstract class JdbcDaoSupport extends org.springframework.jdbc.core.support.JdbcDaoSupport{
	
	/**
	 * 默认的每页显示的记录数
	 */
	@SuppressWarnings("unused")
	private final int DEFAULT_PAGE_MAX_SIZE = 10;
	
	/**
	 * 根据sql语句及查询条件得到结果列表
	 * @param query String : sql查询语句
	 * @param values Object[] : 查询条件
	 * @param mapper RowMapper : 结果集映射器，实现Spring的org.springframework.jdbc.core.RowMapper接口
	 * @param start int : 开始记录数[0,)
	 * @param count int : 结果集数目[1,)
	 * @return List : 内含mapper映射后的Object实例
	 */
	public List findByQuery(final String query, final Object[] values, final RowMapper mapper, int start, int count){
		String queryPlus = getPaginationQuery(query, start, count);
		return super.getJdbcTemplate().query(queryPlus, values, mapper);
	}
	
	public List findByQuery(final String query, final Object[] values, final RowMapper mapper){
		//String queryPlus = getPaginationQuery(query, start, count);
		return super.getJdbcTemplate().query(query, values, mapper);
	}
	
	/**
	 * 根据sql语句取得求总记录数所需的sql语句
	 * @param query String : sql查询语句
	 * @return String : 求总记录数所需sql查询语句
	 */
	public abstract String getTotalCountQuery(String query);
	
	/**
	 * 根据sql语句取得分页所需的sql语句
	 * @param query String : sql查询语句
	 * @param start int : 开始的记录数[0,)
	 * @param count int : 所取的记录数[1,)
	 * @return String : 分页所需的sql语句 
	 */
	public abstract String getPaginationQuery(String query, int start, int count);
	
	/**
	 * 暂时只能用在支持子查询的数据库上
	 * @param query String : 查询语句
	 * @param values Object[] : 查询所需要的参数值
	 * @return int : 查询记录结果总数
	 */
	public int getTotalCount(String query, Object[] values){
		String queryPlus = getTotalCountQuery(query);
		Integer total=super.getJdbcTemplate().queryForObject(queryPlus, Integer.class, values);
		return total==null?0:total;
		//return super.getJdbcTemplate().queryForInt(queryPlus, values);
	}
	
	/**
	 * 查询得到结果列表
	 * @param query String : 查询的sql语句
	 * @param mapper RowMapper : 结果集映射器，实现Spring的org.springframework.jdbc.core.RowMapper接口
	 * @param start int : 开始记录数[0,)
	 * @param count int : 结果集数目[1,)
	 * @return List : 内含mapper映射后的Object实例
	 */
	public List findByQuery(String query,RowMapper mapper,int start,int count){
		return super.getJdbcTemplate().query(getPaginationQuery(query,start,count), mapper);
	}
	
	public List findByQuery(String query,RowMapper mapper){
		return super.getJdbcTemplate().query(query, mapper);
	}
	
	/**
	 * 查询到得结果记录集的数量
	 * @param query String : 查询的sql语句
	 * @return int : 结果集的数量[0,)
	 */
	public int getTotalCount(String query){
		Integer total= super.getJdbcTemplate().queryForObject(getTotalCountQuery(query), Integer.class);
 
		return total==null?0:total;
		//return super.getJdbcTemplate().queryForInt(getTotalCountQuery(query));
	}
	
	/**
	 * 根据查询语句及查询条件返回第一条结果
	 * @param query String : 带?号的查询sql语句
	 * @param value Object : 查询条件
	 * @return Object : 第一条结果，内含Object[]实例
	 */
	public Object findFirstByQuery(final String query,final Object value){
		return findFirstByQuery(query,new Object[]{value});
	}
	
	/**
	 * 根据查询语句及查询条件返回第一条结果
	 * @param query String : 查询sql语句
	 * @param mapper RowMapper : 实现org.springframework.jdbc.core.RowMapper接口的实例
	 * @return Object : 第一条结果，内含RowMapper返回的Object
	 */
	public Object findFirstByQuery(final String query,final RowMapper mapper){
		List list = getJdbcTemplate().query(query,mapper);
		return (list == null || list.size() <= 0) ? null : list.get(0);		
	}
	
	/**
	 * 根据查询语句及查询条件返回第一条结果
	 * @param query String : 带?号的查询sql语句
	 * @param values Object[] : 查询条件
	 * @param mapper RowMapper : 实现org.springframework.jdbc.core.RowMapper接口的实例
	 * @return Object : 第一条结果，内含RowMapper返回的Object
	 */
	public Object findFirstByQuery(final String query, final Object[] values,final RowMapper mapper){
		List list = getJdbcTemplate().query(query, values,mapper);
		return (list == null || list.size() <= 0) ? null : list.get(0);
	}
	
	/**
	 * 根据查询语句及查询条件返回第一条结果
	 * @param query String : 带?号的查询sql语句
	 * @param value Object : 查询条件
	 * @param mapper RowMapper : 实现org.springframework.jdbc.core.RowMapper接口的实例
	 * @return Object : 第一条结果，内含RowMapper返回的Object
	 */
	public Object findFirstByQuery(final String query, Object value,final RowMapper mapper){
		return findFirstByQuery(query,new Object[]{value},mapper);
	}
	
	/**
	 * 插入一条数据，适用于主键为整数的主键为native的数据库，如mysql
	 * @param sql String : 带?号的insert语句
	 * @param values Object[] : ?号对应的参数值
	 * @return int : 主键值
	 */
	public int save(final String sql,final Object[] values){
		KeyHolder key = new GeneratedKeyHolder();
		final String insert = sql.toString();
		getJdbcTemplate().update(new PreparedStatementCreator(){
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
				for(int i=1;i<=values.length;i++){
					setObject(ps,i,values[i-1]);
				}
				return ps;
			}
		},key);	
		if(key.getKey() != null){
			return key.getKey().intValue();
		}
		return 0;
	}
	
	/**
	 * 执行数据更新
	 * @param sql String : 带?号的update语句
	 * @param values Object[] : ?号对应的参数值
	 * @return int : 更新影响的数据条数
	 */
	public int update(final String sql,final Object[] values){
		final String update = sql.toString();
		return getJdbcTemplate().update(new PreparedStatementCreator(){
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(update);
				for(int i=1;i<=values.length;i++){
					setObject(ps,i,values[i-1]);
				}
				return ps;
			}
		});					
	}
	
	public int update(final String sql,final Object value){
		return update(sql,new Object[]{value});
	}	
	
	private void setObject(PreparedStatement pstmt,int i,Object value) throws SQLException{		
		if(value instanceof Integer){
			pstmt.setInt(i, ((Integer) value).intValue());
		}else if(value instanceof String){
			pstmt.setString(i, (String) value);
		}else if(value instanceof Date){
			pstmt.setTimestamp(i, new Timestamp(((Date)value).getTime()));							
		}else if(value instanceof Timestamp){
			pstmt.setTimestamp(i, (Timestamp) value);
		}else if(value instanceof Double){
			pstmt.setDouble(i, ((Double) value).doubleValue());
		}else if(value instanceof Float){
			pstmt.setFloat(i, ((Float) value).floatValue());
		}else if(value instanceof Long){
			pstmt.setLong(i, ((Long) value).longValue());
		}else{
			pstmt.setObject(i, value);
		}		
	}
	
}
