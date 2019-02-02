package wang.congjun.demo07.serial;

import lombok.Data;

@Data
public class Message {
    private Integer len;
    private  String className;
    private byte[] data;
}
