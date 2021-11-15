package com.ifmo.lesson16.print2;

import javax.sql.rowset.Predicate;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class PrintServer {

    private int port;
    private Set<String> users;

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    public PrintServer(int port) {
        this.port = port;
        users = new HashSet<>();
    }

    private void start() throws IOException {
        try (ServerSocket ssocket = new ServerSocket(port)) {
            System.out.println("Server started on " + ssocket);

            while (true) {
                accept(ssocket, (message, host) -> {
                    switch (message.getSender()){
                        case "/server_time":
                            SocketAddress serverAddr = PrintClient.parseAddress(host + ":" + message.getPort());
                            PrintClient.sendPrintMessage(new Message(System.currentTimeMillis(), "/server_time", "/server_time"), serverAddr);
                            break;
                        case "/ping":
                            SocketAddress serverAddr1 = PrintClient.parseAddress(host + ":" + message.getPort());
                            PrintClient.sendPrintMessage(new Message(System.currentTimeMillis(), "/ping", "/ping"), serverAddr1);
                            break;
                        case "/list_users":
                            SocketAddress serverAddr2 = PrintClient.parseAddress(host + ":" + message.getPort());
                            break;
                        default:
                            users.add(message.getSender());
                            printMessage(message, host);
                    }
                });
            }
        }
    }

    public static void accept(ServerSocket ssocket, AcceptRun acceptRun) throws IOException {
        Socket sock = ssocket.accept();

        try {
            String host = sock.getInetAddress().getHostAddress();
            acceptRun.run(process(sock), host);
        }
        catch (ClassNotFoundException e) {
            System.err.println("Wrong message was received");

            e.printStackTrace();
        }
        finally {
            sock.close();
        }
    }

    private static Message process(Socket sock) throws IOException, ClassNotFoundException {
        String host = sock.getInetAddress().getHostAddress();
        Message message;

        try (ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());
             OutputStream out = sock.getOutputStream()) {
            Object obj = objIn.readObject();

            message = (Message)obj;
        }
        catch (IOException | ClassNotFoundException | RuntimeException e) {
            System.err.println("Failed process connection from: " + host);

            e.printStackTrace();

            throw e;
        }
        return message;
    }

    private void printMessage(Message msg, String senderAddr) {
        System.out.printf("%s (%s) at %s wrote: %s\n", msg.getSender(), senderAddr, format.format(new Date(msg.getTimestamp())), msg.getText());
    }

    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("Port must be specified");

        int port = Integer.parseInt(args[0]);

        PrintServer printServer = new PrintServer(port);

        printServer.start();
    }
}
