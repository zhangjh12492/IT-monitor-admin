package com.wfj.exception.dal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.common.MailUsedStatus;
import com.wfj.exception.common.SysConstants;
import com.wfj.exception.dal.entity.MailConfigInfo;
import com.wfj.exception.dal.service.MailConfigInfoService;
import com.wfj.exception.email.util.MailUtil;
import com.wfj.exception.email.vo.MailSenderVo;
import com.wfj.exception.util.DataTableJson;
import com.wfj.exception.util.PropertiesLoad;
import com.wfj.persist.SimpleGenericDAO;
import com.wfj.util.Page;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/10/12.
 */
@Service("mailConfigInfoService")
public class MailConfigInfoServiceImpl implements MailConfigInfoService {

    private static final Logger log= LoggerFactory.getLogger(MailConfigInfoServiceImpl.class);

    private SimpleGenericDAO<MailConfigInfo, Integer> mailConfigInfoDao;

    @Override
    public void insertMailConfigInfo(MailConfigInfo mailConfigInfo) throws Exception {
        if(mailConfigInfo.getUsedStatus().equals(MailUsedStatus.USEDING.getCode())){
            Integer mailCount=this.selectMailCountByUsedStatus(mailConfigInfo);
            if(mailCount<=0){   //如果没有正在使用的邮箱实例,则修改使用状态，并将当前邮件配置改为修改后的
                mailConfigInfoDao.insert(mailConfigInfo);
                updatePropertiesMailConfigInfo(mailConfigInfo);
            }else{
                mailConfigInfo.setUsedStatus(MailUsedStatus.UNUSED.getCode());
                mailConfigInfoDao.insert(mailConfigInfo);
            }
        }else{
            mailConfigInfoDao.insert(mailConfigInfo);
        }
    }

    @Override
    public void deleteMailConfigInfo(MailConfigInfo mailConfigInfo) throws Exception {
        mailConfigInfoDao.deleteById(mailConfigInfo);
    }

    @Override
    public void updateMailConfigInfo(MailConfigInfo mailConfigInfo) throws Exception {
        if(mailConfigInfo.getUsedStatus().equals(MailUsedStatus.USEDING.getCode())){
            Integer mailCount=this.selectMailCountByUsedStatus(mailConfigInfo);
            if(mailCount<=0){   //如果没有正在使用的邮箱实例,则修改使用状态,并将当前邮件配置修改
                mailConfigInfoDao.updateById(mailConfigInfo);
                updatePropertiesMailConfigInfo(mailConfigInfo);
            }else{
                mailConfigInfo.setUsedStatus(MailUsedStatus.UNUSED.getCode());
                mailConfigInfoDao.updateById(mailConfigInfo);
            }
        }else{
            mailConfigInfoDao.updateById(mailConfigInfo);
        }

    }

    @Override
    public DataTableJson selectMailConfigInfoPage(Page page) throws Exception {
        log.info("开始分页查询短信配置信息,page:{}", JSONObject.toJSONString(page));
        DataTableJson json = new DataTableJson();
        List<MailConfigInfo> list = mailConfigInfoDao.pageQueryByCond(page);
        Integer count = mailConfigInfoDao.pageQueryCountByCond(page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        log.info("分页查询短信配置信息结束,count:{},list.size:{},list:{}",count,list.size(),list.toString());
        return json;
    }

    @Override
    public Integer selectMailCountByUsedStatus(MailConfigInfo mailConfigInfo) throws Exception {
        Integer mainCount= (Integer) mailConfigInfoDao.findByStatementCond("selectMailCountByUsedStatus",mailConfigInfo);
        return mainCount;
    }
    @Override
    public MailConfigInfo selectMailInfoByUsedStatus(MailConfigInfo mailConfigInfo) throws Exception {
        List<MailConfigInfo> mailConfigInfoList=mailConfigInfoDao.findListByStatementCond("selectMailInfoByUsedStatus",mailConfigInfo);
        if(mailConfigInfoList!=null&&mailConfigInfoList.size()>0){
            return mailConfigInfoList.get(0);
        }
        return null;
    }
    /**
     * 给多人发送邮件
     * @param mailSenderVo
     * @return
     * @throws Exception
     */
    public Boolean sendMail(MailSenderVo mailSenderVo) throws Exception{
        MailUtil.sendMail(mailSenderVo);
        return true;
    }

    /**
     * 修改资源文件中的邮箱配置
     * @param mailConfigInfo
     */
    private void updatePropertiesMailConfigInfo(MailConfigInfo mailConfigInfo){
        PropertiesLoad.putProperties(SysConstants.MAIL_NICKNAME, mailConfigInfo.getMailNickName());
        PropertiesLoad.putProperties(SysConstants.MAIL_PASSWORD,mailConfigInfo.getMailPassword());
        PropertiesLoad.putProperties(SysConstants.MAIL_SENDER,mailConfigInfo.getMailSender());
        PropertiesLoad.putProperties(SysConstants.MAIL_SERVER,mailConfigInfo.getMailServer());
        PropertiesLoad.putProperties(SysConstants.MAIL_USERNAME, mailConfigInfo.getMailUserName());
    }

    @Autowired
    public void setMesProcessTrackDao(SqlSessionTemplate sqlSessionTemplate) {
        this.mailConfigInfoDao = new SimpleGenericDAO<MailConfigInfo, Integer>(sqlSessionTemplate, MailConfigInfo.class);
    }
}





