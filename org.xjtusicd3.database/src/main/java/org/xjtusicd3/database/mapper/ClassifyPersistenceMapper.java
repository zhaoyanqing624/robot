package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ClassifyPersistence;

public interface ClassifyPersistenceMapper  extends IBaseDao<ClassifyPersistence, String>{
	/*
	 * robot-分类
	 */
	@Select("SELECT classifyId,classifyName FROM classify WHERE parentId='0'")
	public List<ClassifyPersistence> FirstClassify_robot();
	@Select("SELECT classifyId,classifyName FROM classify WHERE parentId=#{0}")
	public List<ClassifyPersistence> FirstClassify_robot2(int paramString);
}
