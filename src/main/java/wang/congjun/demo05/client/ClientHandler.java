package wang.congjun.demo05.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import wang.congjun.demo05.listener.CallbackFutureListener;
import wang.congjun.demo05.pojo.ReqMsg;
import wang.congjun.demo05.pojo.RespMsg;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ClientHandler extends SimpleChannelInboundHandler<RespMsg> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx, RespMsg msg) throws Exception {
            System.out.println("接受到服务端的消息："+msg);
    }

    /**
     * 在channel初始化的时候，发送10条数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ReqMsg reqMsg;
        for(int i = 0;i<10;i++){
            reqMsg = new ReqMsg();
            reqMsg.setId(i);
            reqMsg.setMsg("hello "+i);
            ctx.writeAndFlush(reqMsg).addListener(new CallbackFutureListener());
        }
    }
}
