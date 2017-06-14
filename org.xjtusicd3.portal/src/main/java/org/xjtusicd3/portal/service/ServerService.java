package org.xjtusicd3.portal.service;

import java.util.List;

import org.xjtusicd3.database.helper.ServerHelper;
import org.xjtusicd3.database.model.ServerPersistence;

public class ServerService {

	public static List<ServerPersistence> getServer(){
		List<ServerPersistence> list = ServerHelper.getServer();
		return list;
	}
}
