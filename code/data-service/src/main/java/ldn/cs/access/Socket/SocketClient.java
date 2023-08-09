package ldn.cs.access.Socket;

import ldn.cs.access.kafaka.KafkaProducer;
import ldn.cs.access.kafaka.KafkaTopicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Component
public class SocketClient {
    @Autowired
    private KafkaProducer producer;

    private final String TOPIC = "topic_data_service_original_message";

    // 使用静态Map存储已连接的Socket对象
    private static final Map<String, Socket> socketObject = new HashMap<>();

    public void connectAndListen(String serverIP, int serverPort) {
        try {
            Socket clientSocket = new Socket(serverIP, serverPort);
            System.out.println("Connected to server: " + serverIP + ":" + serverPort);

            // 获取输入流，用于接收来自服务器的消息
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // 不断监听服务器的消息
            new Thread(() -> {
                try {
                    String message;
                    while ((message = reader.readLine()) != null) {
                        System.out.println(message);
                        producer.sendMessage(TOPIC, message);
                    }
                } catch (IOException e) {
                    System.err.println("Error while reading from server: " + e.getMessage());
                }
            }).start();

            // 将Socket对象存储到Map中
            String key = serverIP + ":" + serverPort;
            socketObject.put(key, clientSocket);
        } catch (IOException e) {
            System.err.println("Error while connecting to server " + serverIP + ":" + serverPort + ": " + e.getMessage());
        }
    }

    public void closeConnection(String serverIP, int serverPort) {
        String ipPort = serverIP + ":" + serverPort;
        if (!socketObject.containsKey(ipPort)) {
            return;
        }
        Socket socket = socketObject.get(ipPort);
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
                socketObject.remove(ipPort);
                System.out.println("Connection to server " + serverIP + ":" + serverPort + " closed.");
            } catch (IOException e) {
                System.err.println("Error while closing connection to server " + serverIP + ":" + serverPort + ": " + e.getMessage());
            }
        } else {
            System.out.println("No active connection to server " + serverIP + ":" + serverPort);
        }
    }

    public Map<String, Socket> getIpPort() {
        return socketObject;
    }
}
