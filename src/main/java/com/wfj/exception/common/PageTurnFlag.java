package com.wfj.exception.common;

/**
 * Created by Administrator on 2015/10/21.
 */
public enum PageTurnFlag {

    PREVIOUSPAGE("previous"),
    NEXTPAGE("next");

    private String code;

    PageTurnFlag(String code){
        this.code=code;
    }
    public String getCode(){
        return code;
    }

}
