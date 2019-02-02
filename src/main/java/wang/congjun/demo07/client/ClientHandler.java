package wang.congjun.demo07.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import wang.congjun.demo07.listener.CallbackFutureListener;
import wang.congjun.demo07.pojo.Person;

/**
 *
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            System.out.println("接受到服务端的消息："+msg);
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Person person = new Person();
        person.setId(2112263);
        person.setName("susan");
        Person.Book book = new Person.Book();
        book.setId(11255);
        person.setBook(book);
        ctx.writeAndFlush(person)
                .addListener(new CallbackFutureListener());
    }
}
