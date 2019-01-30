package wang.congjun.demo02.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/28.
 */
public class HwServer {
    public static void main(String[] args) throws Exception{
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        ChannelFuture cf = b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new HwServerInitChannel())
                .bind(8899)
                .sync();
        cf.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();

    }
}
