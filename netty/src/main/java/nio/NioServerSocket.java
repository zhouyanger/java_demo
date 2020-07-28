package nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;


public class NioServerSocket {
    public static void main(String[] args) throws IOException {
        //. 得到一个 Selector 对象 间谍 
        Selector selector = Selector.open();
        // 得到一个 ServerSocketChannel 对象
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定一个端口号
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //  设置非阻塞方式
        serverSocketChannel.configureBlocking(false);
        // 把 ServerSocketChannel 对象注册给 Selector 对象
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动了....");
         // 循环等待接入的客户端
        while (true){
            //获取可用的通道
            int readyChannel = selector.select();
            if(readyChannel==0)continue;
            //获取可用的通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                //移除当前的key
                iterator.remove();
                //处理当前的key,根据就绪状态，调用相应的方法
                //连接事件
                if (selectionKey.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置阻塞模式
                    socketChannel.configureBlocking(false);
                    //注册到select，监听可读事件
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    System.out.println(socketChannel.getRemoteAddress().toString().substring(1)+"上线了....");
                }
                if (selectionKey.isReadable()){
                    //获取就绪的通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //创建buffer
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    //读取客户端的信息
                    int read = socketChannel.read(byteBuffer);
                    if (read>0){
                        String msg = socketChannel.getRemoteAddress().toString().substring(1)+"发来信息："+new String(byteBuffer.array());
                        System.out.println(msg);
                        //将通道再次这次进selector，监听可读事件
                        socketChannel.register(selector,SelectionKey.OP_READ);
                        //广播到其他client
                        broadCast(selector,socketChannel,msg);
                        //清空buffer数据
                        byteBuffer.clear();
                    }
                }
              
            }
        }
    }
    public static void broadCast(Selector selector,SocketChannel sourceChannel,String msg) throws IOException{
        //获取所有接入的客户端
        Set<SelectionKey> keys = selector.keys();
        //向其他客户端发送消息
        keys.forEach(a -> {
            SelectableChannel targetChannel = a.channel();
            //剔除自己通道
            if (targetChannel instanceof  SocketChannel && targetChannel!=sourceChannel){
                try {
                     SocketChannel dest = (SocketChannel) targetChannel;
                    ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                    dest.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
} 
