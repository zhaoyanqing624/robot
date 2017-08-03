package org.xjtusicd3.portal.test;

public class TestCommunityImpl implements TestCommunity
{

	// 注册用户与管理员拥有的功能

	public void answerTopic()
	{
		System.out.println("可以发表,回复帖子");
	}

	// 管理员拥有的功能

	public void deleteTopic()
	{
		System.out.println("可以删除帖子!");
	}
}