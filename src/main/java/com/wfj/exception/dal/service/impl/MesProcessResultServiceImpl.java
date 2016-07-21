package com.wfj.exception.dal.service.impl;

import com.wfj.exception.dal.entity.MesAllProcessReq;
import com.wfj.exception.dal.entity.MesEarlyWarnInfo;
import com.wfj.exception.dal.entity.SysInfo;
import com.wfj.exception.dal.service.*;
import com.wfj.exception.dal.vo.SmsSendVo;
import com.wfj.exception.email.vo.MailSenderVo;
import com.wfj.exception.util.PropertiesLoad;
import com.wfj.exception.util.StringUtils;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.sysmanager.model.Syuser;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
/**
 * reduct统计数据完成后执行的service
 * @ClassName: MesProcessResultServiceImpl
 * @author ZJHao
 * @date 2015-8-31 下午3:03:07
 *
 */
@Service("mesProcessResultService")
public class MesProcessResultServiceImpl implements MesProcessResultService{

	private static final Logger log=LoggerFactory.getLogger(MesProcessResultServiceImpl.class);
	private SimpleGenericDAO<Syuser, Integer> userInfoDao;
	private SimpleGenericDAO<MesEarlyWarnInfo, Integer> mesEarlyWarnInfoDao;
	@Resource(name = "smsService")
    private SmsService smsService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SysInfoService sysInfoService;
	@Autowired
	private MailConfigInfoService mailConfigInfoService;
	
	@Override
	public String mesCountProcessResult(MesAllProcessReq mesAllReq) throws Exception {
		SmsSendVo smsSendVo = new SmsSendVo();	//短信对象
		smsSendVo.setRequestorid("WFJ_VC");
		smsSendVo.setType("01");

		MailSenderVo mailSenderVo=new MailSenderVo();	//邮件对象
		mailSenderVo.setSubject(PropertiesLoad.getProperties("exception.monitor.web.mail.subject"));

        mesAllReq.setSysCode(mesAllReq.getCode());
		log.debug("reduce统计后的数据:{}",mesAllReq.toString());
		List<MesEarlyWarnInfo> listEarlyWarn=mesEarlyWarnInfoDao.findListByStatementCond("selectBySysCode", mesAllReq);	//获取此系统下的所有的消息发送配置信息
		log.debug("查询所有的系统预警配置信息,判断异常数量是否达到阀值,发送短信或者邮件...{}",listEarlyWarn.toString());
		List<Syuser> listUser=null;	//获取此系统下的所有的用户
		SysInfo sysInfo=null;		//根据系统码获取系统信息
		String subject="异常监控平台";
		String mainBody="";
		MesEarlyWarnInfo mesEarlyWarnInfo=null;
		if(listEarlyWarn!=null&&listEarlyWarn.size()>0){
			mesEarlyWarnInfo=listEarlyWarn.get(0);
		}else{
			mesEarlyWarnInfo=new MesEarlyWarnInfo(true);
		}

		/**
		 * 异常发送短信
		 */
		if (mesAllReq.getErrorCount() >= mesEarlyWarnInfo.getErrorCountMin()) {    //如果统计后的异常数量大于等于配置的异常数量,发送邮件或者短信
			List<String> listPhone = new ArrayList<String>();	//存放电话列表
			List<String> mailReceiverList=new ArrayList<>();	//存放邮件列表
			if(listUser==null||listUser.size()<=0){
				listUser=userInfoService.findUserBySysCode(mesAllReq.getCode());
			}
			if(sysInfo==null){
				sysInfo=sysInfoService.selectSysInfoBySysCode(mesAllReq.getSysCode());
			}
			mainBody="【"+sysInfo.getSysName()+"--"+sysInfo.getSysCode()+"】" + "系统异常级别异常数量为【" + mesAllReq.getErrorCount() + "】条,达到系统要求上限【" + mesEarlyWarnInfo.getErrorCountMin() + "】条";
			for (Syuser user : listUser) {
				log.debug("Errorcount  用户 :{}", user.toString());
				if (StringUtils.isNotBlank(user.getEmail())) {
					mailReceiverList.add(user.getEmail());
				}
				if (StringUtils.isNotBlank(user.getTel()))
					listPhone.add(user.getTel());    //用户手机
			}
			if(listPhone!=null&&listPhone.size()>0){
				smsSendVo.setMobilephone(listPhone);
				smsSendVo.setMessagecontent(mainBody);
				smsService.sendSms(smsSendVo);
			}else{
				log.info("暂未找到要发送短信的用户-------");
			}
			if(mailReceiverList!=null&&mailReceiverList.size()>0){
				mailSenderVo.setMaiBody(mainBody);
				mailSenderVo.setReceivers(mailReceiverList);
				mailConfigInfoService.sendMail(mailSenderVo);
			}else{
				log.info("暂未找到要发送邮件的用户");
			}
		}


		/**
		 * 警告发送短信
		 */
		if (mesAllReq.getWarnCount() >= mesEarlyWarnInfo.getWarnCountMin()) {        //如果统计后的警告数量大于等于配置的异常数量,发送邮件或者短信
			List<String> listPhone = new ArrayList<String>();	//存放电话列表
			List<String> mailReceiverList=new ArrayList<>();	//存放邮件列表
			if(listUser==null||listUser.size()<=0){
				listUser=userInfoService.findUserBySysCode(mesAllReq.getCode());
			}
			if(sysInfo==null){
				sysInfo=sysInfoService.selectSysInfoBySysCode(mesAllReq.getSysCode());
			}
			mainBody="【"+sysInfo.getSysName()+"--"+sysInfo.getSysCode()+"】" + "系统警告级别异常数量为【" + mesAllReq.getWarnCount() + "】条,达到系统要求上限【" + mesEarlyWarnInfo.getWarnCountMin() + "】条";
			for (Syuser user : listUser) {
				log.debug("Warncount  用户 :{}", user.toString());
				if (StringUtils.isNotBlank(user.getEmail()))
					mailReceiverList.add(user.getEmail());
				if (StringUtils.isNotBlank(user.getTel()))
					listPhone.add(user.getTel());    //用户手机
			}
			if(listPhone!=null&&listPhone.size()>0){
				smsSendVo.setMobilephone(listPhone);
				smsSendVo.setMessagecontent(mainBody);
				smsService.sendSms(smsSendVo);
			}else{
				log.info("暂未找到要发送短信的用户-------");
			}
			if(mailReceiverList!=null&&mailReceiverList.size()>0){
				mailSenderVo.setMaiBody(mainBody);
				mailSenderVo.setReceivers(mailReceiverList);
				mailConfigInfoService.sendMail(mailSenderVo);
			}else{
				log.info("暂未找到要发送邮件的用户");
			}
		}
		return "true";
	}

	
	private String sendEmail(Syuser user,MesAllProcessReq mesAllReq){
		
		return "true";
	}
	
	
	
	@Autowired
	public void setMesProcessTrackDao(SqlSessionTemplate sqlSessionTemplate) {
		this.userInfoDao = new SimpleGenericDAO<Syuser, Integer>(sqlSessionTemplate, Syuser.class);
		this.mesEarlyWarnInfoDao = new SimpleGenericDAO<MesEarlyWarnInfo, Integer>(sqlSessionTemplate, MesEarlyWarnInfo.class);
	}

}
