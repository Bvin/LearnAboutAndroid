package cn.bvin.app.proguard;


import java.io.Serializable;

/**
 * Created by bvin on 2016/10/26.
 */

public class Foo implements Serializable{


    /**
     * ret : THOPT_70003
     * msg : 送达区域ID不能为空！
     * fare : null
     * disPlayName : null
     * deliverTypeCode : 52
     * deliverTypeTitle : 商家自配送
     */

    //@SerializedName("ret")
    private String ret;

    //@SerializedName("msg")
    private String msg;

    //@SerializedName("fare")
    private Object fare;

    //@SerializedName("disPlayName")
    private Object disPlayName;

    //@SerializedName("deliverTypeCode")
    private String deliverTypeCode;

    //@SerializedName("deliverTypeTitle")
    private String deliverTypeTitle;

    public String getRet() {
        return ret;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getFare() {
        return fare;
    }

    public Object getDisPlayName() {
        return disPlayName;
    }


    public String getDeliverTypeCode() {
        return deliverTypeCode;
    }


    public String getDeliverTypeTitle() {
        return deliverTypeTitle;
    }

}
