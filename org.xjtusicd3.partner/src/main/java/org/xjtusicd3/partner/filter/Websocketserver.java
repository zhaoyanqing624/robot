package org.xjtusicd3.partner.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@ServerEndpoint("/websocketserver")
public class Websocketserver {
	private static SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Map<String, Websocketserver> online = new HashMap<String, Websocketserver>();
	private static int count = 0;
	private Session session;
	private String username;

	/**
	 * 握手成功（建立连接成功）时调用方法
	 * 
	 * @throws IOException
	 */
	@OnOpen
	public void onOpen(Session session) throws IOException {
		System.out.println("有新用户连接。");
	}

	/**
	 * 关闭连接时调用方法
	 * 
	 * @throws IOException
	 */
	@OnClose
	public void onClose() throws IOException {
		online.remove(username);
		for (String s : online.keySet()) {
			sendMessage("{username:'"+username+"',type:4}", online.get(s).getSession());
		}
		System.out.println("有用户退出！");
	}

	/**
	 * 收到客户端消息时调用方法
	 * 
	 * @throws IOException
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		JSONObject messagejson = JSONObject.fromObject(message);
		// type为接收的消息类型，1：新用户登录、2:聊天信息、3、4：用户退出
		switch ((int) messagejson.get("type")) {
		case 1:
			this.session = session;
			this.username=messagejson.getString("username");
			online.put((String) messagejson.get("username"), this);
			messagejson.put("onlinelist",JSONArray.fromObject(online.keySet()).toString());
			for (String s : online.keySet()) {
				sendMessage(messagejson.toString(), online.get(s).getSession());
			}
			break;
		case 2:
			for(String s:online.keySet()){
				messagejson.put("isSelf", online.get(s).getSession().equals(session));
				if(messagejson.get("sendto").equals(s)||messagejson.get("username").equals(s))
				sendMessage(messagejson.toString(), online.get(s).getSession());
			}
			System.out.println(messagejson.toString());
			break;
		case 4:
//			online.remove((String) messagejson.get("username"));			
			System.out.println(messagejson.get("username"));
			break;
		}
	}

	/**
	 * 发生错误时调用方法
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误！");
		error.printStackTrace();
	}

	/**
	 * 发送消息到客户端
	 * 
	 * @param message
	 * @param session
	 * @throws IOException
	 */
	public void sendMessage(String message, Session session) throws IOException {
		System.out.println("发送消息");
		session.getBasicRemote().sendText(message);
	}

	/**
	 * @return 获取当前在线人数
	 */
	private int getcount() {
		// TODO Auto-generated method stub
		return this.count;
	}

	/**
	 * 当前在线人数加一
	 */
	private void addcount() {
		this.count += 1;
	}

	/**
	 * 当前在线人数减一
	 */
	private void subcount() {
		this.count -= 1;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
