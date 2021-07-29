package io.github.harvies.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * 客户端处理器
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //对于每个传入的消息都会被调用
        FullHttpResponse response = (FullHttpResponse) msg;

        ByteBuf content = response.content();
        HttpHeaders headers = response.headers();

        System.out.println("content:" + System.getProperty("line.separator") + content.toString(CharsetUtil.UTF_8));
        System.out.println("headers:" + System.getProperty("line.separator") + headers.toString());
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //通道连接成功被调用
        URI url = new URI("/test");
        String meg = "hello";

        //配置HttpRequest的请求数据和一些配置信息
        FullHttpRequest request = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_0, HttpMethod.GET, url.toASCIIString(), Unpooled.wrappedBuffer(meg.getBytes("UTF-8")));

        request.headers()
                .set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8")
                //开启长连接
                .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
                //设置传递请求内容的长度
                .set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());

        //发送数据
        ctx.writeAndFlush(request);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //当前批量读取中的最后一条消息,连接关闭时也会被调用
        System.out.println(ctx);
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //读取异常时被调用
        super.exceptionCaught(ctx, cause);
    }
}
