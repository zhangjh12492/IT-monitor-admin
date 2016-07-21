package com.wfj.exception.dal.service.impl;

import com.wfj.exception.dal.entity.MesEarlyWarnInfo;
import com.wfj.exception.dal.entity.SysInfo;
import com.wfj.exception.dal.service.*;
import com.wfj.exception.dal.vo.SmsSendVo;
import com.wfj.exception.email.vo.MailSenderVo;
import com.wfj.exception.util.PropertiesLoad;
import com.wfj.exception.util.StringUtils;
import com.wfj.sysmanager.model.Syuser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * zookeepr处理后调用的service
 * @ClassName: ZkProcessServiceImpl
 * @author ZJHao
 * @date 2015年9月23日 下午1:51:07
 *
 */
@Service("zkProcessService")
public class ZkProcessServiceImpl implements ZkProcessService{

	private static final Logger log=LoggerFactory.getLogger(MesProcessResultServiceImpl.class);
	@Resource(name = "smsService")
    private SmsService smsService;
	@Resource
	private SysInfoService sysInfoService;
	@Resource
	private MesEarlyWarnService mesEarlyWarnService;
	@Resource
	private UserInfoService userInfoService;
	@Autowired
	private MailConfigInfoService mailConfigInfoService;
	/**
	 * 根据系统存活的实例数量,发送邮件或者短信
	 * @Title: sendEmailSmsBySysCount
	 * @author Administrator
	 * @throws Exception
	 * @throws
	 * @date 2015年9月23日 下午1:51:27
	 */
	@Override
	public void sendEmailSmsBySysCount(final Integer sysChildCount,final String sysCode) throws Exception {
//		new Thread(){
//			@Override
//			public void run() {
				try {
					SmsSendVo smsSendVo = new SmsSendVo();
					List<String> listPhone = new ArrayList<String>();	//存放电话列表
					smsSendVo.setRequestorid("WFJ_VC");
					smsSendVo.setType("01");

					MailSenderVo mailSenderVo=new MailSenderVo();	//邮件对象
					mailSenderVo.setSubject(PropertiesLoad.getProperties("exception.monitor.web.mail.subject"));
					List<String> mailReceiverList=new ArrayList<String>();

					String mainBody="";

					SysInfo sysInfo=sysInfoService.selectSysInfoBySysCode(sysCode);
					if(sysInfo==null)
						return;
					List<MesEarlyWarnInfo> listMesEarlyWarn=mesEarlyWarnService.findMesEarlyInfoBySysCode(sysCode);
					if(listMesEarlyWarn!=null&&listMesEarlyWarn.size()>0){
						MesEarlyWarnInfo mesWarnInfo=listMesEarlyWarn.get(0);	//获取此系统下的所有的消息发送配置信息
						log.debug("查询所有的系统预警配置信息...{}",mesWarnInfo.toString());
						if(sysChildCount<mesWarnInfo.getSysChildActiveThresholdMaxCount()){	//如果此系统下的
							log.debug("系统:{},存活实例数量为:{},小于最大存活实例数量,{},开始发送短信和邮件",sysCode,sysChildCount,mesWarnInfo.getSysChildActiveThresholdMaxCount());
							List<Syuser> listUser=userInfoService.findUserBySysCode(sysCode);	//获取此系统下的所有的消息发送配置信息

							mainBody="【"+sysInfo.getSysName()+"--"+sysInfo.getSysCode()+"】系统的下的存活实例数量为【"+sysChildCount+"】,小于最大值【"+mesWarnInfo.getSysChildActiveThresholdMaxCount()+"】,请您尽快处理。";
							for(Syuser user:listUser){
								log.debug("Errorcount  用户 :{}",user.toString());

								if(com.wfj.exception.util.StringUtils.isNotBlank(user.getEmail()))
									mailReceiverList.add(user.getEmail());
								if(com.wfj.exception.util.StringUtils.isNotBlank(user.getTel()))
									listPhone.add(user.getTel());	//用户手机
							}
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
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
//		}.start();
		
//	}

}
