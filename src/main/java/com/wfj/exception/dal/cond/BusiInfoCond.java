package com.wfj.exception.dal.cond;

/**
 * Created by Administrator on 2015/10/7.
 */
public class BusiInfoCond extends BaseCond {
    private Integer id;
    private String busiCode;
    private String busiDesc;
    private Integer sysId;
    private String sysDesc;
    private String sysCode;

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

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public String getSysDesc() {
        return sysDesc;
    }

    public void setSysDesc(String sysDesc) {
        this.sysDesc = sysDesc;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    @Override
    public String toString() {
        return "BusiInfoCond{" +
                "id=" + id +
                ", busiCode='" + busiCode + '\'' +
                ", busiDesc='" + busiDesc + '\'' +
                ", sysId=" + sysId +
                ", sysDesc='" + sysDesc + '\'' +
                ", sysCode='" + sysCode + '\'' +
                '}';
    }
}
