package wang.congjun.demo07.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class Client {
    public static void main(String[] args) throws Exception{
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        ChannelFuture cf = b.group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInit())
                .connect("127.0.0.1", 8899)
                .sync();
        System.out.println("客户端初始化完成!!!!");
        cf.channel().closeFuture().sync();
    }
}
