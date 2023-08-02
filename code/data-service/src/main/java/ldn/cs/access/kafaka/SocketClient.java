package ldn.cs.access.kafaka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketClient {
    public static SocketClient instance = new SocketClient();

    private SocketClient() {

    }

    public static SocketClient getInstance() {
        return instance;
    }

    // 使用静态Map存储已连接的Socket对象
    private static final Map<String, Socket> socketObject = new HashMap<>();

    private KafkaProducer kafkaProducer;

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
                        kafkaProducer.sendMessage(message);
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
        String key = serverIP + ":" + serverPort;
        Socket socket = socketObject.get(key);
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
                socketObject.remove(key);
                System.out.println("Connection to server " + serverIP + ":" + serverPort + " closed.");
            } catch (IOException e) {
                System.err.println("Error while closing connection to server " + serverIP + ":" + serverPort + ": " + e.getMessage());
            }
        } else {
            System.out.println("No active connection to server " + serverIP + ":" + serverPort);
        }
    }
}
