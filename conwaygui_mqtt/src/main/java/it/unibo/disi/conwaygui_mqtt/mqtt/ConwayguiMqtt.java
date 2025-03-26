package it.unibo.disi.conwaygui_mqtt.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class ConwayguiMqtt {
	private static ConwayguiMqtt instance = null;
	
	private final String brokerUrl = "tcp://localhost:1883"; // URL del broker Mosquitto
    private final String clientId  = "OutInMqtt"; // ID univoco per il client
    private final String subTopic  = "guiout";
    private final String pubTopic  = "guiin";
    
    private MqttClient client;
    private MqttCallback callbacks = new MqttCallback() {
    	@Override
    	public void connectionLost(Throwable cause) {
    		System.err.println(cause.toString());
    	}

		@Override
		public void messageArrived(String topic, MqttMessage message) throws Exception {
			// TODO Auto-generated method stub
			client.publish(pubTopic, message);
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken token) {
			// TODO Auto-generated method stub
			
		}
    };
    
    //Singleton
    public ConwayguiMqtt getInstance() {
    	if(instance == null) {
    		instance = new ConwayguiMqtt();
    	}
    	
    	return instance;
    }
	
	private ConwayguiMqtt() {
		try {
			client = new MqttClient(brokerUrl, clientId);
			
			client.setCallback(callbacks);
			
			client.connect();
			client.subscribe(subTopic);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
