package wang.congjun.demo05.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import wang.congjun.demo05.MarshallingCodeCFactory;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ClientChannelInit extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(MarshallingCodeCFactory.buildMarshallingEncoder())
                .addLast(MarshallingCodeCFactory.buildMarshallingDecoder())
                .addLast(new ClientHandler());
    }
}
