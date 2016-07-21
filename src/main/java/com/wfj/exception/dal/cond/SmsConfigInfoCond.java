package com.wfj.exception.dal.cond;

/**
 * 短信配置
 * Created by Administrator on 2015/10/12.
 */
public class SmsConfigInfoCond extends BaseCond{

    private Integer id;
    private String smsSendUrl;
    private String usedStatus;

    public String getSmsSendUrl() {
        return smsSendUrl;
    }

    public void setSmsSendUrl(String smsSendUrl) {
        this.smsSendUrl = smsSendUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(String usedStatus) {
        this.usedStatus = usedStatus;
    }
}
