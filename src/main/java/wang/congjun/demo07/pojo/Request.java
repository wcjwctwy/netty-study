package wang.congjun.demo07.pojo;

import lombok.Data;

@Data
public class Request {
    private String className;
    private String method;
    private Object[] params;
    private Class[] paramsTypes;

}
