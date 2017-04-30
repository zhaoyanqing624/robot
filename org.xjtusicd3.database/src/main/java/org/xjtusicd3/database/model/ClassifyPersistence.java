package org.xjtusicd3.database.model;



import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="Classify")
public class ClassifyPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="ClassifyId")
	private String ClassifyId;
	@TableField(columnName="ClassifyName")
	private String ClassifyName;
	@TableField(columnName="ParentId")
	private String ParentId;
	
	
	
	public String getClassifyId() {
		return ClassifyId;
	}
	public void setClassifyId(String classifyId) {
		ClassifyId = classifyId;
	}
	public String getClassifyName() {
		return ClassifyName;
	}
	public void setClassifyName(String classifyName) {
		ClassifyName = classifyName;
	}
	public String getParentId() {
		return ParentId;
	}
	public void setParentId(String parentId) {
		ParentId = parentId;
	}
	
	
}
