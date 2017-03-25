package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="user")
public class WindowsSpiderPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="userId")
	private int userId;
}
