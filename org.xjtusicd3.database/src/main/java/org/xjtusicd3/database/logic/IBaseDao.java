package org.xjtusicd3.database.logic;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.model.Page;


public interface IBaseDao<T, PK> {
	// 这里的方法都将被BasePlugin所拦截，进行sql转换

	@Insert(SqlHelper.BASE_INSERT)
	public void save(T object);

	@Delete(SqlHelper.BASE_DELETE)
	public void delete(T object);

	@Update(SqlHelper.BASE_UPDATE)
	public void update(T object);
	
	@Select(SqlHelper.BASE_LIST)
	public List<T> list(Class<T> cls, Page<T> page);

	@Select(SqlHelper.BASE_FIND_BY_PK)
	public T findByPk(T object);

	@Select(SqlHelper.BASE_SEQ)
	public Long getSeq(T object);

	@Select(SqlHelper.BASE_SELECT_ONE)
	public T selectOne(Class<T> object, String where);
}
