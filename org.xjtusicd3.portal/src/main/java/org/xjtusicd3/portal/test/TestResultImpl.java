package org.xjtusicd3.portal.test;

public class TestResultImpl
{

	private TestCommunity test;

	public void setTest(TestCommunity test)
	{

		this.test = test;
	}

	public void answerTopic()
	{
		test.answerTopic();
	}

	public void deleteTopic()
	{
		test.deleteTopic();
	}
}