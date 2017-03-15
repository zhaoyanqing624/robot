package org.xjtusicd3.database.logic;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.xjtusicd3.common.util.ReflectUtil;
import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
import org.xjtusicd3.database.model.Page;



public class SqlHelper {
	public static final String BASE_INSERT = "base_insert";
	public static final String BASE_DELETE = "base_delete";
	public static final String BASE_UPDATE = "base_update";
	public static final String BASE_FIND_BY_PK = "base_find_by_pk";
	public static final String BASE_LIST = "base_list";
	public static final String BASE_SEQ = "base_seq";
	public static final String BASE_SELECT_ONE = "base_select_one";
	public static final String BASE_LIST_PAGE = "base_list_page";
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String insert(Object object) {
		BEGIN();
		Table table = object.getClass().getAnnotation(Table.class);
		String tablename = table.tablename();
		INSERT_INTO(tablename);
		for (Field f : object.getClass().getDeclaredFields()) {
			TableKey tableKey = f.getAnnotation(TableKey.class);
			TableField tableField = f.getAnnotation(TableField.class);
			String name = tableField == null ? f.getName() : tableField.columnName();
			StringBuffer sbValue = new StringBuffer();
			if (tableKey != null) {
				if (tableKey.strategy() == Strategy.SEQ && ReflectUtil.getFieldValue(object, f.getName()) == null) {
					sbValue.append(tableKey.sequenceName());
				} else {
					sbValue.append("'" + String.valueOf(ReflectUtil.getFieldValue(object, f.getName())) + "'");
				}
			} else {
				if (f.getType().equals(Date.class)) {
					sbValue.append("TO_DATE('" + df.format(ReflectUtil.getFieldValue(object, f.getName())) + "','YYYY-MM-DD HH24:MI:SS')");
				} else {
					sbValue.append("'" + String.valueOf(ReflectUtil.getFieldValue(object, f.getName())) + "'");
				}
			}
			VALUES(name, sbValue.toString());
		}
		String sql = SQL();
		return sql;
	}

	public static String delete(Object object) {
		BEGIN();
		Table table = object.getClass().getAnnotation(Table.class);
		String tablename = table.tablename();
		DELETE_FROM(tablename);
		for (Field f : object.getClass().getDeclaredFields()) {
			TableKey tableKey = f.getAnnotation(TableKey.class);
			if (tableKey != null) {
				TableField tableField = f.getAnnotation(TableField.class);
				String name = tableField == null ? f.getName() : tableField.columnName();
				WHERE(name + " = '" + ReflectUtil.getFieldValue(object, f.getName()) + "'");
			}
		}
		String sql = SQL();
		return sql;
	}

	public static String update(Object object) {
		BEGIN();
		Table table = object.getClass().getAnnotation(Table.class);
		String tablename = table.tablename();
		UPDATE(tablename);
		for (Field f : object.getClass().getDeclaredFields()) {
			TableKey tableKey = f.getAnnotation(TableKey.class);
			TableField tableField = f.getAnnotation(TableField.class);
			String name = tableField == null ? f.getName() : tableField.columnName();
			if (tableKey != null) {
				WHERE(name + " = '" + ReflectUtil.getFieldValue(object, f.getName()) + "'");
			} else {
				if (f.getType().equals(Date.class)) {
					SET(name + " = TO_DATE('" + df.format(ReflectUtil.getFieldValue(object, f.getName())) + "'"+","+"'YYYY-MM-DD HH24:MI:SS')");
					continue;
				}
				String value = String.valueOf(ReflectUtil.getFieldValue(object, f.getName()));
				value = "null".equals(value) ? value : "'" + value + "'";
				SET(name + " = " + value);
			}
		}
		String sql = SQL();
		return sql;
	}

	public static String queryByPk(Object object) {
		BEGIN();
		Table table = object.getClass().getAnnotation(Table.class);
		String tablename = table.tablename();
		SELECT("*");
		FROM(tablename);
		for (Field f : object.getClass().getDeclaredFields()) {
			TableKey tableKey = f.getAnnotation(TableKey.class);
			if (tableKey != null) {
				TableField tableField = f.getAnnotation(TableField.class);
				String name = tableField == null ? f.getName() : tableField.columnName();
				WHERE(name + " = '" + ReflectUtil.getFieldValue(object, f.getName()) + "'");
			}
		}
		String sql = SQL();
		return sql;
	}

	@SuppressWarnings("unchecked")
	public static String list(Object object) {
		Map<String, Object> paraMap = (Map<String, Object>) object;
		Class<?> cls = (Class<?>) paraMap.get("param1");
		Page<?> page = (Page<?>) paraMap.get("param2");
		return getPageSql(cls, page);
	}

	public static String seq(Object object) {
		String sequence = "eop_seq.Nextval";
		for (Field f : object.getClass().getDeclaredFields()) {
			TableKey tableKey = f.getAnnotation(TableKey.class);
			if (tableKey == null) {
				continue;
			}
			sequence = tableKey.sequenceName();
		}
		return "SELECT " + sequence + " from dual";
	}

	/**
	 * 根据page对象获取对应的分页查询Sql语句，这里只做了两种数据库类型，Mysql和Oracle 其它的数据库都 没有进行分页
	 * 
	 * @param page
	 *            分页对象
	 * @param sql
	 *            原sql语句
	 * @return
	 */
	private static String getPageSql(Class<?> cls, Page<?> page) {
		String tablename = ((Table) cls.getAnnotation(Table.class)).tablename();
		StringBuffer sqlBuffer = new StringBuffer("SELECT * FROM " + tablename);
		int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;
		sqlBuffer.insert(0, "SELECT U.*, ROWNUM R FROM (").append(") U WHERE ROWNUM < ").append(offset + page.getPageSize());
		sqlBuffer.insert(0, "SELECT * FROM (").append(") WHERE R >= ").append(offset);
		// 上面的Sql语句拼接之后大概是这个样子：
		// select * from (select u.*, rownum r from (select * from t_user) u  where rownum < 31) where r >= 16
		return sqlBuffer.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	private static String selectOne(Object object) {
		BEGIN();
		Map<String, Object> params = (Map<String, Object>) object;
		Class<?> cls = (Class<?>) params.get("0");
		Table table = cls.getAnnotation(Table.class);
		String tablename = table.tablename();
		for (Field f : cls.getDeclaredFields()) {
			TableField tf = f.getAnnotation(TableField.class);
			if (tf != null) {
				SELECT(tf.columnName() + " as " + f.getName());
			} else {
				SELECT(f.getName());
			}
		}
		FROM(tablename);
		WHERE((String) params.get("1"));
		String sql = SQL();
		System.out.println(sql);
		return sql;
	}

	public static String dispense(String boundSql, Object object) {
		if (BASE_INSERT.equals(boundSql)) {
			return insert(object);
		} else if (BASE_DELETE.equals(boundSql)) {
			return delete(object);
		} else if (BASE_UPDATE.equals(boundSql)) {
			return update(object);
		} else if (BASE_FIND_BY_PK.equals(boundSql)) {
			return queryByPk(object);
		} else if (BASE_LIST.equals(boundSql)) {
			return list(object);
		} else if (BASE_SEQ.equals(boundSql)) {
			return seq(object);
		} else if (BASE_SELECT_ONE.equals(boundSql)) {
			return selectOne(object);
		}else if (BASE_LIST_PAGE.equals(boundSql)) {
			return selectPage(object);
		}
		return boundSql;
	}

	@SuppressWarnings("unchecked")
	private static String selectPage(Object object) {
		Map<String, Object> paraMap = (Map<String, Object>) object;
		Class<?> cls = (Class<?>) paraMap.get("0");
		Page<?> page = (Page<?>) paraMap.get("1");
		
		int offset = (page.getPageNo() - 1) * page.getPageSize() + 1;		
		String tablename = ((Table) cls.getAnnotation(Table.class)).tablename();
		
		BEGIN();
		for (Field f : cls.getDeclaredFields()) {
			TableField tf = f.getAnnotation(TableField.class);
			if (tf != null) {
				SELECT(tf.columnName() + " as " + f.getName());
			} else {
				SELECT(f.getName());
			}
		}
		
		FROM(tablename);								
		String sql = SQL();
		//sql = sql + " where " +page.getSearchCondition();
		sql = sql + " limit "+offset+"," + page.getPageSize();
		System.out.println(sql);
		return sql;
		
	}
}
