package wang.congjun.demo01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2018/12/13.
 */
public class TestHttpServer extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.println(msg);
        ByteBuf buffer = Unpooled.copiedBuffer("Hello world", CharsetUtil.UTF_8);
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,buffer);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,HttpHeaderValues.TEXT_PLAIN)
                .set(HttpHeaderNames.CONTENT_LENGTH,buffer.readableBytes());
        ctx.writeAndFlush(response);
    }
}
