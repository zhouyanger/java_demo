package nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            Socket socket = new Socket("127.0.0.1", 9999);
            String a = scanner.nextLine();
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(a.getBytes());
            InputStream inputStream = socket.getInputStream();
            byte[] b = new byte[1024];
            inputStream.read(b);
            System.out.println("服务器说:" + new String(b));
        }
    }
}
