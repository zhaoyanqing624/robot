package org.xjtusicd3.portal.service;

import java.util.List;

import org.xjtusicd3.database.helper.AdviseHelper;
import org.xjtusicd3.database.model.AdvisePersistence;
 

public class AdviseService {

	public static List<AdvisePersistence> getAdvise(){
		 
		List<AdvisePersistence> advise = AdviseHelper.getAdvise();
		 
		return advise;
	}
}
