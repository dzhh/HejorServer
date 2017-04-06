package com.fly.netty.server;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	
	private final int port;

    public EchoServer(int port) {
        this.port = port;
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);        //1
        new EchoServer(port).start();                //2
    }

    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup(); //3
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)                                //4.创建 ServerBootstrap
             .channel(NioServerSocketChannel.class)        //5 指定使用 NIO 的传输 Channel
             .localAddress(new InetSocketAddress(port))    //6 设置 socket 地址使用所选的端口
             //第7步是关键：在这里我们使用一个特殊的类，ChannelInitializer 。
             //当一个新的连接被接受，一个新的子 Channel 将被创建， ChannelInitializer 
             //会添加我们EchoServerHandler 的实例到 Channel 的 ChannelPipeline。
             //正如我们如前所述，如果有入站信息，这个处理器将被通知。
             .childHandler(new ChannelInitializer<SocketChannel>() { //7 添加 EchoServerHandler 到 Channel 的 ChannelPipeline
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(new EchoServerHandler());
                 }
             });
            //在步骤8，我们绑定的服务器，等待绑定完成。 （调用 sync() 的原因是当前线程阻塞）
            //在第9步的应用程序将等待服务器 Channel 关闭（因为我们 在 Channel 的 CloseFuture 上调用 sync()）。
            //现在，我们可以关闭下 EventLoopGroup 并释放所有资源，包括所有创建的线程（10）。
            ChannelFuture f = b.bind().sync();            //8 绑定的服务器;sync同步  等待服务器关闭+
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();            //9 关闭 channel 和 块，直到它被关闭
        } finally {
            group.shutdownGracefully().sync();            //10 关机的 EventLoopGroup，释放所有资源。
        }
    }


}
