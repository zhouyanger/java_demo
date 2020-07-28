package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NioClient {
    public static void main(String[] args) throws IOException {
        //新建selector
        Selector selector = Selector.open();
        // 得到一个网络通道 
        SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",9999));
        //  设置非阻塞方式
        socketChannel.configureBlocking(false); 
         //注册进selector
        socketChannel.register(selector,SelectionKey.OP_READ);
       //处理数据
        //第一种 接收服务端的数据
        new Thread(new NioClientHandler(selector)) .start();
        //第二种 向服务器发送数据
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if ("bye".equalsIgnoreCase(s)){
            socketChannel.close();
        }
        ByteBuffer allocate = ByteBuffer.wrap(s.getBytes());
        socketChannel.write(allocate);
        allocate.clear();
    }
}
