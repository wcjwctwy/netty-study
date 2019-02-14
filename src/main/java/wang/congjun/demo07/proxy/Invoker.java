package wang.congjun.demo07.proxy;

import wang.congjun.demo07.client.Client;
import wang.congjun.demo07.pojo.Request;
import wang.congjun.demo07.test.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Invoker {
    //    public <T> T  invoker(){
//        Proxy.newProxyInstance(LiuDeHuaProxy.class
//    }
    public static void main(String[] args) {
        Class<?> type = Test.class;
        Object o = Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Client instance = Client.getInstance();
                Request request = new Request();
                request.setMethod(method.getName());
                request.setParams(args);
                request.setClassName(proxy.getClass().getName().indexOf('$') < 0 ? proxy.getClass().getName() : type.getName());
                Object result = instance.sendMsg(request);
                return result;
            }
        });
        System.out.println(o instanceof Test);
        Test t = (Test)o;
        System.out.println(t.test());
        System.out.println(t.test(1,1));
        System.out.println(t.test());
    }
}
