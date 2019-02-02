package wang.congjun.demo07.serial;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.msgpack.core.annotations.Nullable;
import org.msgpack.jackson.dataformat.MessagePackFactory;

public class MessagePackSerialUtils{

    static final byte[] EMPTY_ARRAY = new byte[0];
    private final static ObjectMapper mapper;


    static  {
        mapper = new ObjectMapper(new MessagePackFactory());
        mapper.registerModule((new SimpleModule()));
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    }

    public static <T> byte[] encode(ChannelHandlerContext ctx, T msg) throws Exception {
        if (msg == null) {
            return EMPTY_ARRAY;
        } else {
            try {
                byte[] bytes = mapper.writeValueAsBytes(msg);
                Message message = new Message();
                message.setData(bytes);
                message.setClassName(msg.getClass().getCanonicalName());
                message.setLen(bytes.length);
                return mapper.writeValueAsBytes(message);
            } catch (JsonProcessingException var3) {
                throw new SerializationException("Could not write JSON: " + var3.getMessage(), var3);
            }
        }
    }

    public static  Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        return deserialize(bytes);
    }
    @Nullable
    public static Object deserialize(@Nullable byte[] source) throws SerializationException {
        if (source == null || source.length == 0) {
            return null;
        } else {
            try {
                Message message = mapper.readValue(source, Message.class);
                String className = message.getClassName();
                Class<?> aClass = Class.forName(className);
                return mapper.readValue(message.getData(), aClass);
            } catch (Exception var4) {
                throw new SerializationException("Could not read JSON: " + var4.getMessage(), var4);
            }
        }
    }

}
