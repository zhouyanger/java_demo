package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClientHandler implements Runnable{
    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    private Selector selector;

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        //循环监听服务端的数据
        while (true){
            try {
                //获取就绪的通道
                int select = selector.select();
                if (select == 0) continue;
                //获取可用的channel的集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
                while (selectionKeyIterator.hasNext()){
                    SelectionKey next = selectionKeyIterator.next();
                    //移除该事件
                    selectionKeyIterator.remove();
                    //读取数据
                    if (next.isReadable()){
                        SocketChannel channel = (SocketChannel) next.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        System.out.println(new String(byteBuffer.array()));
                        //把channel再次注册到selector上，监听可读事件
                        channel.register(selector,SelectionKey.OP_READ);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }
       
    }
}
