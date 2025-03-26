const brokerAddr = "localhost"
const brokerPort = 1883
const clientID = "commmqtt"
const subtopic = "guiin"
const pubtopic = "guiout"

var client;

function onMessageArrived(mqttmessage) {
	const message = mqttmessage.payloadString
	
	if (message.startsWith("cell(")) {
		// Estrae la posizione e il colore
	    const [_, x, y, v] = message.match(/cell\((\d+),(\d+),([^)]+)\)/);
	    updateCellColor(parseInt(x), parseInt(y), parseInt(v));
	}else if (message.startsWith("clear")) {
		clearOutArea() //in outarea.js
	}else {
	    addItem(message); //in outarea.js
	}
}

function onConnectionLost(responseObject) {
	console.log("Connection Lost: " + responseObject.errorMessage)
}

function onFailure(responseObject) {
	console.log("Failure: " + responseObject.errorMessage)
}

function createConnection() {
	client = new Paho.MQTT.Client(brokerAddr, brokerPort, clientID)
	
	// CALLBACKS
	client.onMessageArrived = onMessageArrived
	client.onConnectionLost = onConnectionLost
	client.onFailure = onFailure
	
	// Connect
	client.connect()
	
	// Subscribe
	client.subscribe(subtopic)
}

function sendCmdToServer(cmd) {
	var message = new Paho.MQTT.Message(cmd)
	message.destinationName = topic
	message.qos = 2
	
	client.send(message)
}

createConnection()