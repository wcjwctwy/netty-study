package wang.congjun.demo07.proxy;

import wang.congjun.demo07.client.Client;
import wang.congjun.demo07.pojo.InvokeRequest;
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
                InvokeRequest invokeRequest = new InvokeRequest();
                invokeRequest.setMethod(method.getName());
                invokeRequest.setParams(args);
                //将参数类型一并传输过去 防止基本数据类型的拆包问题
                Class<?>[] parameterTypes = method.getParameterTypes();
                invokeRequest.setParamsTypes(parameterTypes);
                invokeRequest.setClassName(proxy.getClass().getName().indexOf('$') < 0 ? proxy.getClass().getName() : type.getName());
                Object result = instance.sendMsg(invokeRequest);
                return result;
            }
        });
        System.out.println(o instanceof Test);
        Test t = (Test)o;
        Integer a = 1;
        int b = 1;
//        System.out.println(t.test());
        System.out.println(t.test(a,b));
//        System.out.println(t.test());
    }
}
