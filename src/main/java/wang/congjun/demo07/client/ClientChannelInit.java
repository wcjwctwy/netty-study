package wang.congjun.demo07.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import wang.congjun.demo07.codec.MyDecoder;
import wang.congjun.demo07.codec.MyEncoder;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ClientChannelInit extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2))
                //增加解码器
                .addLast("msgpack decoder", new MyDecoder())
                //这里设置读取报文的包头长度来避免粘包
                .addLast("frameEncoder", new LengthFieldPrepender(2))
                //增加编码器
                .addLast("msgpack encoder", new MyEncoder())
                .addLast(new ClientHandler());
    }
}
