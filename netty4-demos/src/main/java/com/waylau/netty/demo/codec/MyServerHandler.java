package com.waylau.netty.demo.codec;

import io.netty.channel.*;

/**
 * My ServerHandler.
 * 
 * @since 1.0.0 2019年12月16日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
public class MyServerHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object obj) throws Exception {
		Channel incoming = ctx.channel();

		if (obj instanceof Msg) {
			Msg msg = (Msg) obj;
			System.out.println("Client->Server:" + incoming.remoteAddress() + msg.getBody());
			incoming.write(obj);
		}
		ChannelPromise channelPromise = incoming.voidPromise();

		channelPromise.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				System.out.println("发送完毕");
			}
		});
//		ctx.write(obj, channelPromise)
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
}
