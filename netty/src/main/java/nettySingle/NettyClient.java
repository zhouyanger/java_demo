package nettySingle;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        //1.创建一个 EventLoopGroup 线程组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        //2.创建客户端启动助手
        Bootstrap bootstrap = new Bootstrap();
        
        bootstrap.group(eventLoopGroup)  //3.设置 EventLoopGroup 线程组
        .channel(NioSocketChannel.class)  //使用 NioSocketChannel 作为客户端通道实现
        .handler(new ChannelInitializer<SocketChannel>() {  //创建一个通道初始化对象
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception { //往 Pipeline 链中添加自定义的业务 处理 handler
                socketChannel.pipeline().addLast(new NettyClientHandler());  //客户端业务处理类
                System.out.println("客户端 is ready...");
            }
        });
        //7.启动客户端,等待连接上服务器端(非阻塞)
        ChannelFuture cf = bootstrap.connect("127.0.0.1", 9999).sync();
        //8.等待连接关闭(非阻塞)
        cf.channel().closeFuture().sync();
    }
}
