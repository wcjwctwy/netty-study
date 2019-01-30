package wang.congjun.demo06.client;

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
public class ClientHandler extends SimpleChannelInboundHandler<ResponeseMoudle.Response> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ResponeseMoudle.Response msg) throws Exception {
            System.out.println("接受到服务端的消息："+msg);
    }

    /**
     * 在channel初始化的时候，发送10条数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RequestMoudle.Request.Builder builder = RequestMoudle.Request.newBuilder();
        for(int i = 1;i<11;i++){
            builder.setId(i);
            builder.setMsg("success");
            ctx.write(builder.build());
        }
        ctx.flush();
    }
}
