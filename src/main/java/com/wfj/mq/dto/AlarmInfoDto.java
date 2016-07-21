package com.wfj.mq.dto;

/**
 * Created with IntelliJ IDEA.
 * User: MaYong
 * Date: 2015-04-23
 * Time: 15:57:05
 * To change this template use File | Settings | File Templates.
 */

public class AlarmInfoDto {
    private String systemNo;  //
    private String systemDesc;  //
    private String alarmNo;  //
    private Integer alarmType;  //
    private Integer sid;  //

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public String getSystemDesc() {
        return systemDesc;
    }

    public void setSystemDesc(String systemDesc) {
        this.systemDesc = systemDesc;
    }

    public String getAlarmNo() {
        return alarmNo;
    }

    public void setAlarmNo(String alarmNo) {
        this.alarmNo = alarmNo;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}