package wang.congjun.demo02.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import wang.congjun.demo01.TestHttpServer;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/28.
 */
public class HwServerInitChannel extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new TestHttpServer());
    }
}
