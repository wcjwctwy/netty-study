package wang.congjun.demo07.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class Server7 {
    public static void main(String[] args) throws Exception{
        //创建两个组，分别用来接受客户端情求和处理客户端消息的
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        //配置服务端
        ChannelFuture cf = b.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
                //1
                .childHandler(new ServerChannelInit())
                .bind(8899)
                .sync();
        System.out.println("服务端初始化完成！！！");
        //最后关闭服务端资源
        cf.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
        System.out.println("服务端停止服务！！！");
    }
}
