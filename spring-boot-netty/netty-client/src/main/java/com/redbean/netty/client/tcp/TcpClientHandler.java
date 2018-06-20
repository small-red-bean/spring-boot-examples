package com.redbean.netty.client.tcp;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TcpClientHandler extends ChannelInboundHandlerAdapter {
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("===========");
    }
}
