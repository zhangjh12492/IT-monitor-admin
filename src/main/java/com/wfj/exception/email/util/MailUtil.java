package com.wfj.exception.email.util;

import com.wfj.exception.common.SysConstants;
import com.wfj.exception.email.factory.TemplateFactory;
import com.wfj.exception.email.vo.ConfigLoader;
import com.wfj.exception.email.vo.MailSenderVo;
import com.wfj.exception.util.PropertiesLoad;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;
 
/**
 * 邮件发送工具类
 * 
 * @date 2014年4月26日 下午3:30:04
 * @author 
 * @Description:
 * @project mailUtil
 */
public class MailUtil {
 
	private static final Logger log=LoggerFactory.getLogger(MailUtil.class);
    /**
     * 根据模板名称查找模板，加载模板内容后发送邮件
     * 
     * @Date:2014年4月26日 上午11:02:58
     * @author 
     * @param receiver
     *            收件人地址
     * @param subject
     *            邮件主题
     * @param map
     *            邮件内容与模板内容转换对象
     * @param templateName
     *            模板文件名称
     * @throws IOException
     * @throws TemplateException
     * @throws MessagingException
     * @Description:
     * @return void
     */
    public static void sendMailByTemplate(String receiver, String subject,
            Map<String, String> map, String templateName) throws IOException,
            TemplateException, MessagingException {
        String maiBody = "";
        String server = ConfigLoader.getServer();
        String sender = ConfigLoader.getSender();
        String username = ConfigLoader.getUsername();
        String password = ConfigLoader.getPassword();
        String nickname = ConfigLoader.getNickname();
        MailSender mail = new MailSender(server);
        mail.setNeedAuth(true);
        mail.setNamePass(username, password, nickname);
        maiBody = TemplateFactory.generateHtmlFromFtl(templateName, map);
        mail.setSubject(subject);
        mail.setBody(maiBody);
        mail.setReceiver(receiver);
        mail.setSender(sender);
        mail.sendout();
    }
 
    /**
     * 根据模板名称查找模板，加载模板内容后发送邮件
     * 
     * @Date:2014年4月26日 上午11:02:58
     * @author 
     * @param receiver
     *            收件人地址
     * @param subject
     *            邮件主题
     * @param map
     *            邮件内容与模板内容转换对象
     * @param templateName
     *            模板文件名称
     * @throws IOException
     * @throws TemplateException
     * @throws MessagingException
     * @Description:
     * @return void
     */
    public static void sendMailAndFileByTemplate(String receiver,
            String subject, String filePath, Map<String, String> map,
            String templateName) throws IOException, TemplateException,
            MessagingException {
        String maiBody = "";
        String server = ConfigLoader.getServer();
        String sender = ConfigLoader.getSender();
        String username = ConfigLoader.getUsername();
        String password = ConfigLoader.getPassword();
        String nickname = ConfigLoader.getNickname();
        MailSender mail = new MailSender(server);
        mail.setNeedAuth(true);
        mail.setNamePass(username, password, nickname);
        maiBody = TemplateFactory.generateHtmlFromFtl(templateName, map);
        mail.setSubject(subject);
        mail.addFileAffix(filePath);
        mail.setBody(maiBody);
        mail.setReceiver(receiver);
        mail.setSender(sender);
        mail.sendout();
    }
 
    /**
     * 普通方式发送邮件内容
     * 给单个人发送邮件
     * @Date:2014年4月26日 下午1:20:47
     * @author 
     * @param receiver
     *            收件人地址
     * @param subject
     *            邮件主题
     * @param maiBody
     *            邮件正文
     * @throws IOException
     * @throws MessagingException
     * @Description:
     * @return void
     */
    public static void sendMail(String receiver, String subject, String maiBody)
            throws IOException, MessagingException {

        String server = PropertiesLoad.getProperties(SysConstants.MAIL_SERVER);
        String sender = PropertiesLoad.getProperties(SysConstants.MAIL_SENDER);
        String username = PropertiesLoad.getProperties(SysConstants.MAIL_USERNAME);
        String password = PropertiesLoad.getProperties(SysConstants.MAIL_PASSWORD);
        String nickname = PropertiesLoad.getProperties(SysConstants.MAIL_NICKNAME);
        MailSender mail = new MailSender(server);
        mail.setNeedAuth(true);
        mail.setNamePass(username, password, nickname);
        mail.setSubject(subject);
        mail.setBody(maiBody);
        mail.setReceiver(receiver);
        mail.setSender(sender);
        mail.sendout();
    }
    /**
     * 普通方式发送邮件内容
     * 给多个人发送邮件
     * @Date:2014年4月26日 下午1:20:47
     * @author
     * @throws IOException
     * @throws MessagingException
     * @Description:
     * @return void
     */
    public static void sendMail(MailSenderVo mailSenderVo)
            throws IOException, MessagingException {

        String server = PropertiesLoad.getProperties(SysConstants.MAIL_SERVER);
        String sender = PropertiesLoad.getProperties(SysConstants.MAIL_SENDER);
        String username = PropertiesLoad.getProperties(SysConstants.MAIL_USERNAME);
        String password = PropertiesLoad.getProperties(SysConstants.MAIL_PASSWORD);
        String nickname = PropertiesLoad.getProperties(SysConstants.MAIL_NICKNAME);
        MailSender mail = new MailSender(server);
        mail.setNeedAuth(true);
        mail.setNamePass(username, password, nickname);
        mail.setSubject(mailSenderVo.getSubject());
        mail.setBody(mailSenderVo.getMaiBody());
        mail.setReceiver(mailSenderVo.getReceivers());
        mail.setSender(sender);
        mail.sendout();
    }
 
    /**
     * 普通方式发送邮件内容，并且附带文件附件
     * 
     * @Date:2014年4月26日 下午1:20:47
     * @author 
     * @param receiver
     *            收件人地址
     * @param subject
     *            邮件主题
     * @param filePath
     *            文件的绝对路径
     * @param maiBody
     *            邮件正文
     * 
     * @throws IOException
     * @throws MessagingException
     * @Description:
     * @return void
     */
    public static void sendMailAndFile(String receiver, String subject,
            String filePath, String maiBody) throws IOException,
            MessagingException {
        String server = ConfigLoader.getServer();
        String sender = ConfigLoader.getSender();
        String username = ConfigLoader.getUsername();
        String password = ConfigLoader.getPassword();
        String nickname = ConfigLoader.getNickname();
        MailSender mail = new MailSender(server);
        mail.setNeedAuth(true);
        mail.setNamePass(username, password, nickname);
        mail.setSubject(subject);
        mail.setBody(maiBody);
        mail.addFileAffix(filePath);
        mail.setReceiver(receiver);
        mail.setSender(sender);
        mail.sendout();
    }
 
}