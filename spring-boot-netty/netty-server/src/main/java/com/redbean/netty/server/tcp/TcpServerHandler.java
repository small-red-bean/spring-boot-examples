package com.redbean.netty.server.tcp;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TcpServerHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        System.out.println("server accepted msg:" + msg);
        channelHandlerContext.channel().writeAndFlush("server accepted msg:" + msg);
    }
}
