package com.fly.netty.server.channel;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLEngine;

import com.fly.netty.codec.protobuf.MsgClient2Server;
import com.fly.netty.server.handler.SubReqServerHandler;
import com.fly.util.WebUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class ServerTCPChannelInitializer <C extends Channel> extends ChannelInitializer<Channel> {

	private String tlsMode = "CSA";
//	private String tlsMode = "CA";
	// 设置6秒检测chanel是否接受过心跳数据
	private static final int READ_WAIT_SECONDS = 10;
	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		
	 // protobufDecoder仅仅负责编码，并不支持读半包，所以在之前，一定要有读半包的处理器。
	 // 有三种方式可以选择：
	 // 使用netty提供ProtobufVarint32FrameDecoder
	 // 继承netty提供的通用半包处理器 LengthFieldBasedFrameDecoder
	 // 继承ByteToMessageDecoder类，自己处理半包

//		SSLEngine engine = SecureChatSslContextFactory.getServerContext(tlsMode,
//					    System.getProperty("user.dir") + "/src/com/fly/netty/ssl/sChat.jks",
//					    System.getProperty("user.dir") + "/src/com/fly/netty/ssl/sChat.jks")
//				       .createSSLEngine();
		SSLEngine engine = SecureChatSslContextFactory.getServerContext(
						WebUtil.PATH + "WEB-INF/key/sChat.jks",
						WebUtil.PATH + "WEB-INF/key/sChat.jks")
				       .createSSLEngine();
		
		engine.setUseClientMode(false);
	    engine.setNeedClientAuth(true);
		
	    pipeline.addLast("ssl", new SslHandler(engine));

		// 半包的处理
	    pipeline.addLast(new ProtobufVarint32FrameDecoder());
		// 需要解码的目标类
	    pipeline.addLast(new ProtobufDecoder(MsgClient2Server.Msg.getDefaultInstance()));
	    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
	    pipeline.addLast(new ProtobufEncoder());
//	    pipeline.addLast(new SubReqServerHandler());
	    pipeline.addLast("pong", new IdleStateHandler(READ_WAIT_SECONDS, 0, 0, TimeUnit.SECONDS));
	    pipeline.addLast("handle", new SubReqServerHandler());
	}

}
