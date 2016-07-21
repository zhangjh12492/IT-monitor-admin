package com.wfj.exception.dal.entity;

/**
 * Created by Administrator on 2015/10/12.
 */
public class BusiInfoTmp {
    private Integer id;
    private String busiCode;
    private String busiDesc;
    private String sysCode;

    public BusiInfoTmp(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getBusiDesc() {
        return busiDesc;
    }

    public void setBusiDesc(String busiDesc) {
        this.busiDesc = busiDesc;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }
}
