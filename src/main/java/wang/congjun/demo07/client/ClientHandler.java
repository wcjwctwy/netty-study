package wang.congjun.demo07.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.concurrent.CountDownLatch;

/**
 *
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private CountDownLatch lathc;
    private Object result;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            result=msg;
            // 释放锁执行后续代码
            lathc.countDown();
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }
    public void resetLatch(CountDownLatch lathc) {
        this.lathc = lathc;
    }

    public Object getResult() {
        return result;
    }
}
