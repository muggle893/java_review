package network.udp;

import java.io.IOException;
import java.net.*;

public class UDPEchoServer {
    public static void main(String[] args) {
        UDPEchoServer udpEchoServer = new UDPEchoServer(9090);
        udpEchoServer.start();
    }
    DatagramSocket socket;
    public UDPEchoServer(int port) {
        // 绑定服务端监听的端口
        try {
            socket =  new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                // 接收客户端发送过来的数据
                byte[] data = new byte[4096];
                DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
                this.socket.receive(datagramPacket);
                String request = new String(datagramPacket.getData(),0,  datagramPacket.getLength());
                // 返回响应给客户端
                DatagramPacket resp = new DatagramPacket(datagramPacket.getData(), datagramPacket.getLength(), new InetSocketAddress(datagramPacket.getAddress(), datagramPacket.getPort()));
                socket.send(resp);
                // 输出客户端传输的内容
                System.out.println(resp.getAddress().toString()  +":" +  resp.getPort() + "->:" + request );
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
