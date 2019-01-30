package wang.congjun.demo05.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import wang.congjun.demo05.listener.CallbackFutureListener;
import wang.congjun.demo05.pojo.ReqMsg;
import wang.congjun.demo05.pojo.RespMsg;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ServerHandler extends SimpleChannelInboundHandler<ReqMsg> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ReqMsg msg) throws Exception {
        System.out.println("接受到数据是：" + msg);
        //返回响应
        RespMsg respMsg = new RespMsg();
        respMsg.setCode(200);
        respMsg.setMsg("success");
        respMsg.setReqMsg(msg);
        ctx.writeAndFlush(respMsg).addListener(new CallbackFutureListener());
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
