package com.wfj.exception.dal.entity;

import java.util.List;

/**
 * Created by MaYong on 2015/8/21.
 */
public class PcdsSymenu {

    private String id;
    private String pid;
    private String text;
    private String src;
    private List<PcdsSymenu> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public List<PcdsSymenu> getChildren() {
        return children;
    }

    public void setChildren(List<PcdsSymenu> children) {
        this.children = children;
    }
}
