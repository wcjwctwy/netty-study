package wang.congjun.demo07.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import wang.congjun.demo07.listener.CallbackFutureListener;
import wang.congjun.demo07.pojo.Instance;
import wang.congjun.demo07.pojo.Person;
import wang.congjun.demo07.pojo.Request;
import wang.congjun.demo07.test.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private static Map<String, Instance> instances = new HashMap<>();

    {
        Instance instance = new Instance();
        instance.setClazz(Test.class);
        instance.setObj(new Test() {
            @Override
            public String test() {
                return "Hello world";
            }

            @Override
            public Integer test(int a, int b) {
                return a+b;
            }

            @Override
            public Integer test(Integer a, int b) {
                return a-b;
            }
        });
        instances.put(Test.class.getName(),instance);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = null;
        try {
            if (msg instanceof Person) {
                result = "服务端已经接受数据！！";
            }
            //转换数据
            System.out.println("接受到数据是：" + msg);
            if (msg instanceof Request) {
                Request req = (Request) msg;
                String className = req.getClassName();
                Instance instance = instances.get(className);
                Class<?> aClass = instance.getClazz();
                Object[] params = req.getParams();
                Object o = instance.getObj();
                if (params == null || params.length == 0) {
                    Method method = aClass.getMethod(req.getMethod());
                    result = method.invoke(o);
                } else {
                    Method method = aClass.getMethod(req.getMethod(), req.getParamsTypes());
                    result = method.invoke(o, params);
                }


            }
            //返回响应
//            ByteBuf resp = Unpooled.copiedBuffer("服务端已经接受数据！！", CharsetUtil.UTF_8);
            ctx.writeAndFlush(result)
                    .addListener(new CallbackFutureListener());
        } finally {
            //释放
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("=============test通道激活============");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("=============test通道激活ing============");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("=============test通道数据读取完毕！！============");
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("=============test通道激活出现异常============");
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }


}
