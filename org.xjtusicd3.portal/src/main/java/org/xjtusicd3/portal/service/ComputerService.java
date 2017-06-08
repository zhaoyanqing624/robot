package org.xjtusicd3.portal.service;

import java.util.List;

import org.xjtusicd3.database.helper.ComputerHelper;
import org.xjtusicd3.database.model.ComputerPersistence;

public class ComputerService {

	public static List<ComputerPersistence> getComputer(){
		List<ComputerPersistence> list = ComputerHelper.getComputer();
		return list;
	}
}
