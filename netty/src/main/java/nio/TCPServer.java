package nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true){
            Scanner scanner = new Scanner(System.in);
            Socket accept = serverSocket.accept();
            byte[] a = new byte[1024];
            InputStream inputStream = accept.getInputStream();
            inputStream.read(a);
            System.out.println("服务器接收到消息:"+new String(a));
            OutputStream outputStream = accept.getOutputStream();
            String b = scanner.nextLine();
            outputStream.write(b.getBytes());
            accept.close();
        }
    }
}
