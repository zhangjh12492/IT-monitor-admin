package com.wfj.exception.dal.vo;

import java.util.List;

/**
 * Created by MaYong on 2015/8/28.
 */
public class SmsSendVo {
    private String keycode;
    private String messagecontent;
    private String organizationid;
    private String requestorid;
    private String type;
    private List<String> mobilephone;

    public String getKeycode() {
        return keycode;
    }

    public void setKeycode(String keycode) {
        this.keycode = keycode;
    }

    public String getMessagecontent() {
        return messagecontent;
    }

    public void setMessagecontent(String messagecontent) {
        this.messagecontent = messagecontent;
    }

    public String getOrganizationid() {
        return organizationid;
    }

    public void setOrganizationid(String organizationid) {
        this.organizationid = organizationid;
    }

    public String getRequestorid() {
        return requestorid;
    }

    public void setRequestorid(String requestorid) {
        this.requestorid = requestorid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(List<String> mobilephone) {
        this.mobilephone = mobilephone;
    }
}
