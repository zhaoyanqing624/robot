package org.xjtusicd3.database.logic;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.xjtusicd3.common.util.ReflectUtil;


@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })

public class BasePlugin implements Interceptor{

	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
		// 通过反射获取到当前RoutingStatementHandler对象的delegate属性
		StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
		BoundSql boundSql = delegate.getBoundSql();
		String oldSql = boundSql.getSql();
		if (oldSql.startsWith("base_")) {
			Object obj = boundSql.getParameterObject();
			String newSql = SqlHelper.dispense(oldSql.trim(), obj);
			ReflectUtil.setFieldValue(boundSql, "sql", newSql);
		}
		return invocation.proceed();
	}

	public Object plugin(Object object) {
		return Plugin.wrap(object, this);
	}

	public void setProperties(Properties arg0) {
		
	}

}
