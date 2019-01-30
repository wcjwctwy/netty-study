package wang.congjun.demo01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2018/12/13.
 */
public class TestServer {
    public static void main(String[] args) {
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();
        NioEventLoopGroup childGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup,childGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestChannelInitialHandler());
            ChannelFuture sync = bootstrap.bind(8899).sync();
            sync.channel().closeFuture().sync();
        }catch (Exception e){
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
