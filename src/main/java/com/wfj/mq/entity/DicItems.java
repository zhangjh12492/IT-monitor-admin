package com.wfj.mq.entity;

/**
 * Created with IntelliJ IDEA.
 * User: MaYong
 * Date: 2015-04-23
 * Time: 15:57:05
 * To change this template use File | Settings | File Templates.
 */

public class DicItems {
    private String dicName;  //
    private Integer status;  //
    private String dicItem;  //
    private Integer dicValueSecond;  //
    private Integer sid;  //
    private String dicValue;  //

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDicItem() {
        return dicItem;
    }

    public void setDicItem(String dicItem) {
        this.dicItem = dicItem;
    }

    public Integer getDicValueSecond() {
        return dicValueSecond;
    }

    public void setDicValueSecond(Integer dicValueSecond) {
        this.dicValueSecond = dicValueSecond;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }

}