package org.xjtusicd3.portal.service;

import java.util.List;

import org.xjtusicd3.database.helper.ConfigureHelper;
import org.xjtusicd3.database.model.ConfigurePersistence;

public class ConfigureService {

	public static List<ConfigurePersistence> getAllConfig() throws Exception{
		List<ConfigurePersistence> cp = ConfigureHelper.getAllConfig();
		return cp;		
	}
}
