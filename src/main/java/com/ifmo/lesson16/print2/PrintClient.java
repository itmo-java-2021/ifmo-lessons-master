package com.ifmo.lesson16.print2;

import java.io.*;
import java.net.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PrintClient {

    private SocketAddress serverAddr;

    private String name;

    private Scanner scanner;

    private int responsePort = 4443;

    public PrintClient(SocketAddress serverAddr, Scanner scanner) {
        this.serverAddr = serverAddr;
        this.scanner = scanner;
    }

    private void start() throws IOException {
        System.out.println("Enter your name: ");

        name = scanner.nextLine();

        outer:
        while (true) {
            System.out.println("Enter message to send: ");

            String msg = scanner.nextLine();

            switch (msg){
                case "/exit":
                    break outer;
                case "/nick":
                    System.out.println("Enter new name:");
                    name = scanner.nextLine();
                    continue;
                case "/myaddr":
                    printAddresses();
                    continue;
                case "/server_time":
                    serverTime();
                    continue;
                case "/ping":
                    ping();
                    continue;
                default:
                    buildAndSendMessage(msg);
            }
        }
    }

    private void ping() throws IOException {
        Message message = new Message(System.currentTimeMillis(), "/ping", "/ping", responsePort);
        try (ServerSocket ssocket = new ServerSocket(responsePort)) {
            for (int i = 0; i < 4; i++) {
                sendPrintMessage(message, serverAddr);
                long time = System.nanoTime();
                PrintServer.accept(ssocket, (message1, host) -> {
                    long time2 = System.nanoTime();
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
                    long durationInMs = TimeUnit.MILLISECONDS.convert((time2 - time), TimeUnit.NANOSECONDS);
                    System.out.println("ping: " + durationInMs + " ms");
                });
            }
        }
    }

    private void serverTime() throws IOException {
        Message message = new Message(System.currentTimeMillis(), "/server_time", "/server_time", responsePort);
        sendPrintMessage(message, serverAddr);
        try (ServerSocket ssocket = new ServerSocket(responsePort)) {
            PrintServer.accept(ssocket, (message1, host) -> {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
                System.out.println("server_time: " + format.format(new Date(message1.getTimestamp())));
            });
        }
    }

    private void printAddresses() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();

        while(e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();

            Enumeration ee = n.getInetAddresses();

            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();

                System.out.println(i.getHostAddress());
            }
        }
    }

    private void buildAndSendMessage(String msg) throws IOException {
        Message message = new Message(System.currentTimeMillis(), name, msg);

        sendPrintMessage(message, serverAddr);

        System.out.println("Sent: " + message);
    }

    public static void sendPrintMessage(Message msg, SocketAddress serverAddr) throws IOException {
        try (Socket sock = new Socket()) {
            sock.connect(serverAddr);

            try (OutputStream out = sock.getOutputStream();
                 InputStream in = sock.getInputStream()) {
                ObjectOutputStream objOut = new ObjectOutputStream(out);

                objOut.writeObject(msg);

                objOut.flush();
            }
        }
    }

    public static SocketAddress parseAddress(String addr) {
        String[] split = addr.split(":");
        return new InetSocketAddress(split[0], Integer.parseInt(split[1]));
    }

    public static void main(String[] args) throws IOException {
        String addr = null;

        if (args != null && args.length > 0)
            addr = args[0];

        Scanner scanner = new Scanner(System.in);

        if (addr == null) {
            System.out.println("Enter server address");

            addr = scanner.nextLine();
        }

        PrintClient client = new PrintClient(parseAddress(addr), scanner);

        client.start();
    }
}
