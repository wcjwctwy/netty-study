package wang.congjun.demo07.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import wang.congjun.demo07.listener.CallbackFutureListener;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            //转换数据
            System.out.println("接受到数据是：" + msg);
            //返回响应
//            ByteBuf resp = Unpooled.copiedBuffer("服务端已经接受数据！！", CharsetUtil.UTF_8);
            ctx.writeAndFlush("服务端已经接受数据！！")
                    .addListener(new CallbackFutureListener());
        }finally {
            //释放
            ReferenceCountUtil.release(msg);
        }
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
