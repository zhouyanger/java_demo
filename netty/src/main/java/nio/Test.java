package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test {
    public static void main(String[] args) throws IOException {

       
   
        File file=new File("basic.txt");
 FileInputStream fis=new FileInputStream(file);
 FileChannel fc=fis.getChannel();
 ByteBuffer buffer=ByteBuffer.allocate((int)file.length());
        int read = fc.read(buffer);
        buffer.flip(); //重置buff位置为起始位置
        while(read!=-1){
            while (buffer.hasRemaining()){
                System.out.println((char)buffer.get());
            }
        }
        buffer.clear(); //清空buffer
        fc.close();
    }
}
