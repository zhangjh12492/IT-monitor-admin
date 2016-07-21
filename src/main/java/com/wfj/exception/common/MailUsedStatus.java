package com.wfj.exception.common;

/**
 * Created by Administrator on 2015/10/12.
 */
public enum MailUsedStatus {

    UNUSED("0"),    //未使用的
    USEDING("1");   //正在使用
    private String code;

    public String getCode() {
        return code;
    }


    private MailUsedStatus(String code){
        this.code=code;
    }
}
