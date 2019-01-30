package wang.congjun.demo06.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import wang.congjun.demo05.MarshallingCodeCFactory;
import wang.congjun.demo06.pojo.RequestMoudle;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ServerChannelInit extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        //对服务端管道进行配置，编解码，Handler的处理顺序等等
        ch.pipeline()
                //处理数据的分割，使得序列化时可以知道开始和结束
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                //处理数据的序列化
                .addLast(new ProtobufEncoder())
                .addLast(new ProtobufDecoder(RequestMoudle.Request.getDefaultInstance()))
                .addLast(new ServerHandler());
    }
}
