package wang.congjun.demo05.pojo;

import java.io.Serializable;

/**
 * @author WangCongJun
 * Created by WangCongJun on 2019/1/29.
 */
public class ReqMsg implements Serializable {
    private Integer id;
    private String msg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ReqMsg{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }
}
