package wang.congjun.demo07.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import wang.congjun.demo07.serial.MessagePackSerialUtils;

public class MyEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object obj, ByteBuf buf) throws Exception {
		//序列化操作
		byte[] bytes =MessagePackSerialUtils.encode(ctx,obj);
		//netty操作,将对象序列化数组传入ByteBuf
		buf.writeBytes(bytes);

	}
}