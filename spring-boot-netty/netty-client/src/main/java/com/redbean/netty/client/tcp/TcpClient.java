package com.redbean.netty.client.tcp;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class TcpClient {
    private Bootstrap bootstrap;
    private NioEventLoopGroup nioEventLoopGroup;

    public TcpClient() {
        bootstrap = new Bootstrap();
        nioEventLoopGroup = new NioEventLoopGroup();
        bootstrap.group(nioEventLoopGroup).channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast("ping", new IdleStateHandler(60, 20, 60 * 10, TimeUnit.SECONDS));
                pipeline.addLast("handler", new TcpClientHandler());
            }
        });
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
    }

    public Channel getChannel(String host, int port) {
        Channel channel = null;
        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port);
            channelFuture.addListener(new ConnectionListener());
            channel = channelFuture.sync().channel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channel;
    }

    public void shutdown() {
        nioEventLoopGroup.shutdownGracefully();
    }

    public void sendMessage(String message) throws InterruptedException {
        Channel channel = getChannel("172.19.100.248", 9999);
        channel.writeAndFlush(message).sync();
    }

    public static void main(String[] args) throws InterruptedException {
        TcpClient tcpClient = new TcpClient();
        tcpClient.sendMessage("hello world......");
//        tcpClient.shutdown();
    }
}
