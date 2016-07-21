package com.wfj.exception.email.vo;

import java.util.List;

/**
 * Created by Administrator on 2015/10/15.
 */
public class MailSenderVo {
    private List<String> receivers;    //收件人地址
    private String subject;     //邮件主题
    private String maiBody;     //邮件正文

    public String getSubject() {
        return subject;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMaiBody() {
        return maiBody;
    }
    public void setMaiBody(String maiBody) {
        this.maiBody = maiBody;
    }
}
