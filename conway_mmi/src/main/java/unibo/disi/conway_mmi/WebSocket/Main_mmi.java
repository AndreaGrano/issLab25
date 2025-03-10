package unibo.disi.conway_mmi.WebSocket;

public class Main_mmi {
	public static void main(String[] args) {
		Conway_mmi_WS mmi = new Conway_mmi_WS();
		
		System.out.println("LET'S PLAY SOME CONWAY'S GAME OF LIFE!");
		mmi.play();
		System.out.println("OK, BYE");
	}
}
