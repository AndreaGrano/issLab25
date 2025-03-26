package it.unibo.disi.conwaygui_mqtt.controller;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ConwayguiControllerMqtt {
	@Value("${spring.application.name}")
	String appName;
	
	@Value("${server.port}")
	String serverport;
	
	private static final String ownerName = "mqtt";
	private static String currOwner = "";	
	private static String serverIp = "";
	private static String clientIp = "";
	private boolean started = false;
	
	public ConwayguiControllerMqtt() {
		super();
	}
	
	@GetMapping("/")
	public String homepage(Model model, HttpServletRequest request) {
		clientIp = getClientIp(request);
		System.out.println("ConwayguiControllerMqtt: homepage request from: " + clientIp);
		
		return "guiPageMirror";
	}
	
	@PostMapping("/connect")
	public String connect(@RequestParam("name") String name) {
		String page = "";
		
		System.out.println("ConwayguiControllerMqtt: connect reqeust from: " + name);
		if(name.equals(ownerName)) {
			page = "guiPageOwner";
			
			if(!started) {
				started = true;
			}
		} else {
			page = "guiPageMirror";
		}
		
		return page;
	}
	
	//CHiamata dalla pagina HTML per conoscere l'ip del server
	@RequestMapping("/getserverip")
	@ResponseBody
	public String getServerIp(HttpServletRequest request) {
		String resp = "{\"host\":\"IP\"}";
		
		try {
			// si potrebbe inserire il controllo dell'ip pubblico...
			
			serverIp = System.getenv("HOST_IP"); //in docker-compose
			if(serverIp != null) {
				resp.replace("IP", serverIp);
			} else {
				serverIp = getServerLocalIp();
				if(serverIp == null) { // non ho la rete
					resp.replace("IP", "localhost");
				} else {
					resp.replace("IP", serverIp);
				}
			}
		} catch(Exception e) {
			resp.replace("IP", "localhost");
		}
		
		return resp;
	}

	private String getClientIp(HttpServletRequest request) {
       String remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (remoteAddr == null || "".equals(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
        }

        return  remoteAddr;
    }
	
	private String getServerLocalIp() {		
        try {
            Enumeration<NetworkInterface> interfacce = NetworkInterface.getNetworkInterfaces();
            while (interfacce.hasMoreElements()) {
                NetworkInterface interfaccia = interfacce.nextElement();
                Enumeration<InetAddress> indirizzi = interfaccia.getInetAddresses();
                while (indirizzi.hasMoreElements()) {
                    InetAddress indirizzo = indirizzi.nextElement();
                    if (!indirizzo.isLoopbackAddress() && // Esclude l'indirizzo loopback (127.0.0.1)
                    	indirizzo.getHostAddress().startsWith("192.168")) { 
                        	return indirizzo.getHostAddress();
                    }
                }
            }
            return null;
        } catch (SocketException e) {
            System.err.println("Errore durante la ricerca degli indirizzi IP: " + e.getMessage());
            return null;
        }
	}
	
	public static boolean checkIfOwner(   ) {
 		return currOwner.equals(ownerName);
	}
}
