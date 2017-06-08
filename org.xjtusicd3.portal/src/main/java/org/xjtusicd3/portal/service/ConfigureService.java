package org.xjtusicd3.portal.service;

import java.util.List;

import org.xjtusicd3.database.helper.ConfigureHelper;
import org.xjtusicd3.database.model.ConfigurePersistence;

public class ConfigureService 
{

//	public static String getPartConfig() 
//	{
//		String result = "";
//		List<ConfigurePersistence> list = ConfigureHelper.getPartConfig();
//		for (int i = 0; i < list.size(); i++) 
//		{
//			result += "{\"id\":\""+(i+1)+"\",\"configureProducer\":\""+list.get(i).getPRODUCER()+"\",\"configureName\":\""+list.get(i).getCONFIGURENAME()+"\",\"configureType\":\""+list.get(i).getCONFIGURETYPE()+"\"}";
//			if(i < list.size()-1)
//			{
//				result += ",";
//			}else
//			{
//				result += "";
//			}
//		}
//		System.out.println(result);
//		return result;
//	}
//	public static void main(String[] args) 
//	{
//		getPartConfig();
//	}
	public static List<ConfigurePersistence> getConfigure() 
	{
		List<ConfigurePersistence> configurelist = ConfigureHelper.getAllConfig();
		return configurelist;
		
	}
}
