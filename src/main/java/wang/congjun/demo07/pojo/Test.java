package wang.congjun.demo07.pojo;

import com.google.protobuf.ByteString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/31.
 */
public class Test {
    public static void testHeatbeat() throws Exception{
        TestMessage.HeatBeat.Builder newBuilder = TestMessage.HeatBeat.newBuilder();
        newBuilder.setId(112263);
        newBuilder.setMsg("hello");
        byte[] bytes = newBuilder.build().toByteArray();

        TestMessage.Header.Builder headerBuilder = TestMessage.Header.newBuilder();
        headerBuilder.setContentLength(2);
        headerBuilder.setMetod(TestMessage.Header.Method.HEATBEAT);
        headerBuilder.setVersion("1.0");
        TestMessage.Header header = headerBuilder.build();

        TestMessage.Message.Builder messageBuilder = TestMessage.Message.newBuilder();
        messageBuilder.setHeader(header);
        messageBuilder.setBody(ByteString.copyFrom(bytes));
        TestMessage.Message messageReq = messageBuilder.build();

        TestMessage.Message msg = TestMessage.Message.parseFrom(messageReq.toByteArray());
        TestMessage.Header h = msg.getHeader();
        System.out.println(h.getMetod());
        switch (h.getMetod()){
            case HEATBEAT:
                TestMessage.HeatBeat heatBeat = TestMessage.HeatBeat.parseFrom(msg.getBody());
                System.out.println(heatBeat);
                break;
            case CUSTOM:
                break;
            default:

        }
    }

    public static void testCustom() throws Exception{
        Map<String,String> map = new HashMap<>(1);
        map.put("hello","world");

        TestMessage.Custom.Builder customBuilder = TestMessage.Custom.newBuilder();
        customBuilder.setId(112263);
        customBuilder.setClassName("Json");
        byte[] bytes = customBuilder.build().toByteArray();

        TestMessage.Header.Builder headerBuilder = TestMessage.Header.newBuilder();
        headerBuilder.setContentLength(2);
        headerBuilder.setMetod(TestMessage.Header.Method.HEATBEAT);
        headerBuilder.setVersion("1.0");
        TestMessage.Header header = headerBuilder.build();

        TestMessage.Message.Builder messageBuilder = TestMessage.Message.newBuilder();
        messageBuilder.setHeader(header);
        messageBuilder.setBody(ByteString.copyFrom(bytes));
        TestMessage.Message messageReq = messageBuilder.build();

        TestMessage.Message msg = TestMessage.Message.parseFrom(messageReq.toByteArray());
        TestMessage.Header h = msg.getHeader();
        System.out.println(h.getMetod());
        switch (h.getMetod()){
            case HEATBEAT:
                TestMessage.HeatBeat heatBeat = TestMessage.HeatBeat.parseFrom(msg.getBody());
                System.out.println(heatBeat);
                break;
            case CUSTOM:
                break;
            default:

        }
    }

    public static void main(String[] args) throws Exception {
    }
}
