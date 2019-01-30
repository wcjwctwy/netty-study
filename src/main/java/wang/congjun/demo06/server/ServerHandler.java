package wang.congjun.demo06.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import wang.congjun.demo05.pojo.ReqMsg;
import wang.congjun.demo05.pojo.RespMsg;
import wang.congjun.demo06.listener.CallbackFutureListener;
import wang.congjun.demo06.pojo.RequestMoudle;
import wang.congjun.demo06.pojo.ResponeseMoudle;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ServerHandler extends SimpleChannelInboundHandler<RequestMoudle.Request> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, RequestMoudle.Request msg) throws Exception {
        System.out.println("接受到数据是：" + msg);
        //返回响应
        ResponeseMoudle.Response.Builder builder = ResponeseMoudle.Response.newBuilder();
        builder.setCode(msg.getId())
                .setMsg("get client msg");
        ctx.writeAndFlush(builder.build());
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
