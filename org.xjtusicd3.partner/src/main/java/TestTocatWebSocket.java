import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

public class TestTocatWebSocket {

    public static void main(String[] args) throws URISyntaxException {

        String url = "ws://localhost:8080/t8j8/ws/chat/" + args[0];
        WebSocketClient wc = new WebSocketClient(new URI(url), new Draft_17()) {

	@Override
	public void onOpen(ServerHandshake handshakedata) {
	    System.out.println(handshakedata.getHttpStatusMessage());
	}

	@Override
	public void onMessage(String message) {
	    System.out.println(message);
	}

	@Override
	public void onError(Exception ex) {
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
	}
        };

        wc.connect();

        while (true) {
	Scanner scanner = new Scanner(System.in);
	String message = scanner.nextLine();
	if (message.equals("q")) {
	    wc.close();
	    break;
	}
	scanner.close();
	wc.send(message);
        }
    }
}
