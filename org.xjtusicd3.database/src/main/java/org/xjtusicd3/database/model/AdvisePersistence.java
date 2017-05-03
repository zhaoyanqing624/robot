package org.xjtusicd3.database.model;

import java.util.jar.Attributes.Name;

import javax.swing.text.AbstractDocument.Content;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
@Table(tablename="TBL_Advise")
public class AdvisePersistence
{
	@TableKey(strategy = Strategy.NORMAL)
	@TableField(columnName="ADVISEID")
	private String AdviseId;
	@TableField(columnName="EMAIL")
	private String Email;
	@TableField(columnName="NAME")
	private String Name;
	@TableField(columnName="PHONE")
	private String Phone;
	@TableField(columnName="TEXT")
	private String Content;
	@TableField(columnName="ANNEX")
	private String Attachment;
	@TableField(columnName="USERID")
	private String UserId;
		
	public String getAdviseId()
	{
		return AdviseId;
	}
	public void setAdviseId(String adviseId)
	{
		AdviseId = adviseId;
	}
	public String getEmail()
	{
		return Email;
	}
	public void setEmail(String email)
	{
		Email = email;
	}
	public String getName()
	{
		return Name;
	}
	public void setName(String name)
	{
		Name = name;
	}
	public String getPhone()
	{
		return Phone;
	}
	public void setPhone(String phone)
	{
		Phone = phone;
	}
	public String getContent()
	{
		return Content;
	}
	public void setContent(String content)
	{
		Content = content;
	}
	public String getAttachment()
	{
		return Attachment;
	}
	public void setAttachment(String attachment)
	{
		Attachment = attachment;
	}
	public String getUserId()
	{
		return UserId;
	}
	public void setUserId(String userId)
	{
		UserId = userId;
	}
	 
}
