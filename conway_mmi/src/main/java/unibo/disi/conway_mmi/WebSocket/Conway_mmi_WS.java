package unibo.disi.conway_mmi.WebSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class Conway_mmi_WS {
	private final String discoverUrl = "ws://localhost:7110/getserverip";
	
	private String serverUrl;
	private Session session;
	
	public Conway_mmi_WS() {
		discoverService();
		this.serverUrl = this.serverUrl + "/wsupdates";
		
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		try {
			container.connectToServer(this, new URI(serverUrl));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		
		System.out.println("WS opened and connected with " + serverUrl);
	}
	
	@OnMessage
	public void onMessage(String message) {
		System.out.println("WS message: " + message);
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("WS closed: " + closeReason);
	}
	
	private void discoverService() {
		try {
			URL discoverUrl = new URL(this.discoverUrl);
			HttpURLConnection connection = (HttpURLConnection) discoverUrl.openConnection();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			this.serverUrl = in.readLine();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void play() {
		Basic remote = this.session.getBasicRemote();
		try {
			remote.sendText("clear");
			
			//Setting the initial state of the grid (a cross)
			remote.sendText("cell-1-2");
			remote.sendText("cell-2-1");
			remote.sendText("cell-2-2");
			remote.sendText("cell-2-3");
			remote.sendText("cell-3-2");
			
			//Start the game
			remote.sendText("start");
			
			//Wait some amount of time
			this.wait(5000);
			
			//End the game
			remote.sendText("stop");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
