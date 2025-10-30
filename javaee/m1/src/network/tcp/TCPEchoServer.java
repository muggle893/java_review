package network.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPEchoServer {
    public static int SERVER_PORT = 9090;
    public  ServerSocket serverSocket;
    public ExecutorService threadPool;

    public static void main(String[] args) {
        new TCPEchoServer().start();
    }
    public TCPEchoServer() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            threadPool = Executors.newFixedThreadPool(10);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        while (true) {
            try {
                // 调用ServerSocket的accept方法和客户端建立连接
                Socket accept = serverSocket.accept();
                // 使用线程池让线程池里面的线程来处理和客户端的连接
                threadPool.submit(()->{
                    processConnection(accept);
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void processConnection(Socket socket) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        Scanner scanner = null;
        PrintWriter printWriter = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            scanner = new Scanner(inputStream);
            printWriter = new PrintWriter(outputStream);
            String data = "";
            while (scanner.hasNextLine()) {
                data = scanner.nextLine();
                if (data.equals("exit")) {
                    break;
                }
                // 把消息回响给客户端
                printWriter.println(data);
                printWriter.flush();
                // 输出日志
                System.out.println("[" + socket.getInetAddress().toString() + ":" + socket.getPort() + "]" + ":" + data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
                if (scanner != null) {
                    scanner.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
