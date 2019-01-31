package wang.congjun.demo07.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ServerChannelInit extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        //对服务端管道进行配置，编解码，Handler的处理顺序等等
        ch.pipeline()
                .addLast(new ReadTimeoutHandler(5))
                .addLast(new LoggingHandler("INFO"))
                .addLast(new ServerHandler());
    }
}
