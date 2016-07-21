package com.wfj.exception.util;

/**
 * Created by MaYong on 2015/8/20.
 */
public class MsgReturnDto {

    private String code;
    private String desc;

    public MsgReturnDto() {

    }

    public MsgReturnDto(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
