package wang.congjun.demo04.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            System.out.println("接受到数据是："+msg);
            //返回响应
            ctx.writeAndFlush("服务端已经接受数据！！");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("=============test通道激活============");
        super.channelActive(ctx);
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("=============test通道激活ing============");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("=============test通道数据读取完毕！！============");
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("=============test通道激活出现异常============");
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }


}
