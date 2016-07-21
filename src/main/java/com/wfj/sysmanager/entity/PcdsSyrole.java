package com.wfj.sysmanager.entity;

/**
 * Symenu entity. @author MyEclipse Persistence Tools
 */

public class PcdsSyrole implements java.io.Serializable {

    // Fields

    private String id;
    private String pid;
    private String text;
    private String descript;
    private Integer seq;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

}