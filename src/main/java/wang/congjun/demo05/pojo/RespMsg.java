package wang.congjun.demo05.pojo;

import java.io.Serializable;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class RespMsg implements Serializable {
    private Integer code;
    private String msg;
    private ReqMsg reqMsg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ReqMsg getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(ReqMsg reqMsg) {
        this.reqMsg = reqMsg;
    }

    @Override
    public String toString() {
        return "RespMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", reqMsg=" + reqMsg +
                '}';
    }
}
