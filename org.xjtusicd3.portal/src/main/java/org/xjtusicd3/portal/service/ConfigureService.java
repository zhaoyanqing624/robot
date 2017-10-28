package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.ConfigureHelper;
import org.xjtusicd3.database.model.ConfigureHistoryPersistence;
import org.xjtusicd3.database.model.ConfigurePersistence;
import org.xjtusicd3.portal.view.ChangeIndexView;

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

	/**
	 * author:
	 * abstract:变更列表
	 * data:2017年10月12日17:50:36
	 * @param startNumber 
	 */
	public static List<ChangeIndexView> getConfigureHistory(int startNumber) {
		// TODO Auto-generated method stub
				List<ChangeIndexView> changeIndexViews = new ArrayList<ChangeIndexView>();
				List<ConfigureHistoryPersistence> configureHistoryPersistences = ConfigureHelper.getUpdateCfgs(startNumber);
				for(ConfigureHistoryPersistence configureHistoryPersistence:configureHistoryPersistences){
					ChangeIndexView changeIndexView = new ChangeIndexView();
					List<ConfigurePersistence> configurePersistences =ConfigureHelper.getCfgs(startNumber);
					changeIndexView.setCONFIGURENAME(configurePersistences.get(0).getCONFIGURENAME());
					changeIndexView.setVERSION(configureHistoryPersistence.getVERSION());
					changeIndexView.setURL(configureHistoryPersistence.getURL());
					changeIndexView.setUPDATETIME(configureHistoryPersistence.getUPDATETIME());
					changeIndexView.setREMARKS(configureHistoryPersistence.getREMARKS());
					changeIndexViews.add(changeIndexView);
				}
				
				return changeIndexViews;
			}
}
