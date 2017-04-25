package org.xjtusicd3.portal.service;

import java.util.List;

import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.QuestionPersistence;

public class AnswerService {

	public static List<QuestionPersistence> getFaq(){
		List<QuestionPersistence> list = QuestionHelper.getFaq();
		return list;
	}
}
