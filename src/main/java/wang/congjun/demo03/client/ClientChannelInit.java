package wang.congjun.demo03.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ClientChannelInit extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ClientHandler());
    }
}
