package wang.congjun.demo07.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.util.CharsetUtil;
import wang.congjun.demo07.codec.MyDecoder;
import wang.congjun.demo07.codec.MyEncoder;
import wang.congjun.demo07.listener.CallbackFutureListener;
import wang.congjun.demo07.pojo.Person;
import wang.congjun.demo07.serial.Message;

import java.util.concurrent.CountDownLatch;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class Client {

    //客户端启动类
    private Bootstrap b;
    private ChannelFuture cf;
    private NioEventLoopGroup workGroup;
    //客户端数据处理channel
    private ClientHandler handler;

    public static void main(String[] args) throws Exception {
        Client client = Client.getInstance();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setId(i);
            person.setName("susan");
            Person.Book book = new Person.Book();
            book.setId(11255);
            person.setBook(book);
            client.sendMsg(person, new CallbackFutureListener());
        }
        client.close();
    }

    public static Client getInstance() {
        return NettyClient.client;
    }

    public Object sendMsg(Object msg) {
        Channel channel = cf.channel();
        if (!channel.isOpen()) {
            this.connect();
        }
        if (!channel.isActive()) {
            this.connect();
        }
        channel.writeAndFlush(msg);
        CountDownLatch lathc = new CountDownLatch(1);
        handler.resetLatch(lathc);
        try {
            //开始等待结果返回后执行下面的代码
            lathc.await();
            return handler.getResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ChannelFuture sendMsg(Object msg, ChannelFutureListener listener) {
        Channel channel = cf.channel();
        if (!channel.isOpen()) {
            this.connect();
        }
        if (!channel.isActive()) {
            this.connect();
        }
        return channel.writeAndFlush(msg).addListener(listener);
    }

    public void connect() {
        try {
            cf = b.connect("127.0.0.1", 8899)
                    .sync();
            System.out.println("客户端初始化完成!!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            cf.channel().closeFuture().sync();
            workGroup.shutdownGracefully();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Client() {
        workGroup = new NioEventLoopGroup();
        b = new Bootstrap().group(workGroup).channel(NioSocketChannel.class)
//                .handler(clientChannelInit);
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        handler = new ClientHandler();
                        ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2))
                                //增加解码器
                                .addLast("msgpack decoder", new MyDecoder())
                                //这里设置读取报文的包头长度来避免粘包
                                .addLast("frameEncoder", new LengthFieldPrepender(2))
                                //增加编码器
                                .addLast("msgpack encoder", new MyEncoder())
                                .addLast(handler);
                    }
                });
        this.connect();
    }

    public static class NettyClient {
        static final Client client = new Client();
    }

}
