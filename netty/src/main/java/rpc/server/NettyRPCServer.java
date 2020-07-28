package rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import nettySingle.NettyServerHandler;

public class NettyRPCServer {
    public static void main(String[] args) throws InterruptedException {
        //创建一个线程组，处理客户端连接
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        //创建一个线程组，处理网络事件 io操作
        EventLoopGroup workGroup = new NioEventLoopGroup();
        //创建服务端启动助手来配置参数
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boosGroup,workGroup) //设置两个线程组 EventLoopGroup
                .channel(NioServerSocketChannel.class) //使用NioServerSocketChannel作为服务器端通道实现
                .option(ChannelOption.SO_BACKLOG,128) //设置线程队列中等待连接的个数
                .childOption(ChannelOption.SO_KEEPALIVE,true)  //保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道初始化对象
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception { //往 Pipeline 链中添加自定义的业务 处理 handler
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("encoder",new ObjectEncoder());
                        pipeline.addLast("decoder",new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                        pipeline.addLast(new InvokeHandler());  //服务器端业务处理类
                    }
                });
        // 启动服务器端并绑定端口，等待接受客户端连接(非阻塞)
        ChannelFuture cf = serverBootstrap.bind(9999).sync();
        System.out.println("服务端准备好了");
        //关闭通道，关闭线程池
        cf.channel().closeFuture().sync();
        boosGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }
}
