package com.redbean.netty.client.tcp;


import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class ConnectionListener implements ChannelFutureListener {
    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if(channelFuture.isSuccess()) {
            System.out.println("连接成功......");
        } else {
            System.out.println("连接失败......");
        }
    }
}
