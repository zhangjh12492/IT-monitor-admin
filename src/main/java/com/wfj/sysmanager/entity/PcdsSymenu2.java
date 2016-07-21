package com.wfj.sysmanager.entity;

/**
 * Symenu entity. @author MyEclipse Persistence Tools
 */

public class PcdsSymenu2 implements java.io.Serializable {

    // Fields

    private String id;
    private String pid;
    private String text;
    private String iconcls;
    private String src;
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

    public String getIconcls() {
        return this.iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

}