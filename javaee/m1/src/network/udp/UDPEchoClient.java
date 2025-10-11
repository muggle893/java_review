package network.udp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPEchoClient {
    public static void main(String[] args) throws IOException {
        new UDPEchoClient().start();
    }
    DatagramSocket socket;
    public UDPEchoClient() throws SocketException {
        this.socket = new DatagramSocket();
    }
    public void start() throws IOException {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                // 用户输入
                String s = scanner.nextLine();
                // 构建packet
                byte[] data = s.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(data, data.length, new InetSocketAddress( "127.0.0.1", 9090));
                this.socket.send(datagramPacket);
                // 接收服务端返回的响应
                DatagramPacket resp = new DatagramPacket(new byte[4096], 4096);
                socket.receive(resp);
                System.out.println(new String(resp.getData(), 0, resp.getLength()));
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
