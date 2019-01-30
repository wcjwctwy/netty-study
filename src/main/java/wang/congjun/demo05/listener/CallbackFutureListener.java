package wang.congjun.demo05.listener;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class CallbackFutureListener implements ChannelFutureListener {
    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if (future.isSuccess()) {
            System.out.println("成功发送到服务端消息");
        } else {
            System.out.println("失败服务端消息失败:"+future.cause().getMessage());
            future.cause().printStackTrace();
        }
    }
}
