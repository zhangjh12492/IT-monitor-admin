package com.wfj.exception.dal.cond;

/**
 * 邮件配置信息
 * Created by Administrator on 2015/10/12.
 */
public class MailConfigInfoCond extends BaseCond{


    private Integer id;
    private String mailServer;  //mail代理发送的地址
    private String mailSender;  //发送者的邮件名称
    private String mailNickName;    //发送者昵称
    private String mailUserName;    //用户登陆邮件名称
    private String mailPassword;    //用户登陆邮件密码
    private String usedStatus;  //使用状态

    public String getMailServer() {
        return mailServer;
    }

    public void setMailServer(String mailServer) {
        this.mailServer = mailServer;
    }

    public String getMailSender() {
        return mailSender;
    }

    public void setMailSender(String mailSender) {
        this.mailSender = mailSender;
    }

    public String getMailNickName() {
        return mailNickName;
    }

    public void setMailNickName(String mailNickName) {
        this.mailNickName = mailNickName;
    }

    public String getMailUserName() {
        return mailUserName;
    }

    public void setMailUserName(String mailUserName) {
        this.mailUserName = mailUserName;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(String usedStatus) {
        this.usedStatus = usedStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
