package network.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPEchoClient {
    Socket clientSocket;
    public TCPEchoClient() {
    }

    public static void main(String[] args) {
        new TCPEchoClient().start();
    }
    public void start() {
        PrintWriter out = null;
        Scanner in = null;
        Scanner userInput = null;
        try {
            clientSocket = new Socket("localhost", 9090);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new Scanner(clientSocket.getInputStream());
            userInput = new Scanner(System.in);
            while (userInput.hasNextLine()) {
                String s = userInput.nextLine();
                if (s.equalsIgnoreCase("exit")) {
                    return;
                }
                out.println(s);
                String echoStr = in.nextLine();
                System.out.println("服务器回响：" + echoStr);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null ) {
                in.close();
            }
            if (userInput != null) {
                userInput.close();
            }
            try {
                clientSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
