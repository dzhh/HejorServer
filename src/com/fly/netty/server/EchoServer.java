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
            b.group(group)                                //4.���� ServerBootstrap
             .channel(NioServerSocketChannel.class)        //5 ָ��ʹ�� NIO �Ĵ��� Channel
             .localAddress(new InetSocketAddress(port))    //6 ���� socket ��ַʹ����ѡ�Ķ˿�
             //��7���ǹؼ�������������ʹ��һ��������࣬ChannelInitializer ��
             //��һ���µ����ӱ����ܣ�һ���µ��� Channel ���������� ChannelInitializer 
             //���������EchoServerHandler ��ʵ���� Channel �� ChannelPipeline��
             //����������ǰ�������������վ��Ϣ���������������֪ͨ��
             .childHandler(new ChannelInitializer<SocketChannel>() { //7 ��� EchoServerHandler �� Channel �� ChannelPipeline
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(new EchoServerHandler());
                 }
             });
            //�ڲ���8�����ǰ󶨵ķ��������ȴ�����ɡ� ������ sync() ��ԭ���ǵ�ǰ�߳�������
            //�ڵ�9����Ӧ�ó��򽫵ȴ������� Channel �رգ���Ϊ���� �� Channel �� CloseFuture �ϵ��� sync()����
            //���ڣ����ǿ��Թر��� EventLoopGroup ���ͷ�������Դ���������д������̣߳�10����
            ChannelFuture f = b.bind().sync();            //8 �󶨵ķ�����;syncͬ��  �ȴ��������ر�+
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();            //9 �ر� channel �� �飬ֱ�������ر�
        } finally {
            group.shutdownGracefully().sync();            //10 �ػ��� EventLoopGroup���ͷ�������Դ��
        }
    }


}
