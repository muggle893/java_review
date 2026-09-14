package network.udp;

import java.io.IOException;
import java.net.SocketException;

public class Starter {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                UDPEchoClient udpClient = new UDPEchoClient();
                udpClient.start();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            UDPEchoServer udpServer = new UDPEchoServer( 9090);
            udpServer.start();
        });
        t2.start();
        Thread.sleep(1000);
        t1.start();
    }
}
