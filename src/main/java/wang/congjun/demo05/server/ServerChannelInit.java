package wang.congjun.demo05.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import wang.congjun.demo05.MarshallingCodeCFactory;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ServerChannelInit extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        //对服务端管道进行配置，编解码，Handler的处理顺序等等
        ch.pipeline()
                .addLast(MarshallingCodeCFactory.buildMarshallingEncoder())
                .addLast(MarshallingCodeCFactory.buildMarshallingDecoder())
                .addLast(new ServerHandler());
    }
}
