package com.wfj.exception.dal.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.common.MailUsedStatus;
import com.wfj.exception.common.SysConstants;
import com.wfj.exception.dal.entity.SmsConfigInfo;
import com.wfj.exception.dal.service.SmsConfigInfoService;
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
@Service("smsConfigInfoService")
public class SmsConfigInfoServiceImpl implements SmsConfigInfoService {

    private static final Logger log= LoggerFactory.getLogger(SmsConfigInfoServiceImpl.class);

    private SimpleGenericDAO<SmsConfigInfo, Integer> smsConfigInfoDao;

    @Override
    public void insertSmsConfigInfo(SmsConfigInfo smsConfigInfo) throws Exception {
        if(smsConfigInfo.getUsedStatus().equals(MailUsedStatus.USEDING.getCode())){
            Integer mailCount=this.selectSmsCountByUsedStatus(smsConfigInfo);
            if(mailCount<=0){   //如果没有正在使用的邮箱实例,则修改使用状态,并将资源文件中的短信配置修改
                smsConfigInfoDao.insert(smsConfigInfo);
                updatePropertiesMailConfigInfo(smsConfigInfo);
            }else{
                smsConfigInfo.setUsedStatus(MailUsedStatus.UNUSED.getCode());
                smsConfigInfoDao.insert(smsConfigInfo);
            }
        }else {
            smsConfigInfoDao.insert(smsConfigInfo);
        }
    }

    @Override
    public void deleteSmsConfigInfo(SmsConfigInfo smsConfigInfo) throws Exception {
        smsConfigInfoDao.deleteById(smsConfigInfo);
    }

    @Override
    public void updateSmsConfigInfo(SmsConfigInfo smsConfigInfo) throws Exception {
        if(smsConfigInfo.getUsedStatus().equals(MailUsedStatus.USEDING.getCode())){
            Integer mailCount=this.selectSmsCountByUsedStatus(smsConfigInfo);
            if(mailCount<=0){   //如果没有正在使用的邮箱实例,则修改使用状态,并将资源文件中的短信配置修改
                smsConfigInfoDao.updateById(smsConfigInfo);
                updatePropertiesMailConfigInfo(smsConfigInfo);
            }else{
                smsConfigInfo.setUsedStatus(MailUsedStatus.UNUSED.getCode());
                smsConfigInfoDao.updateById(smsConfigInfo);
            }
        }else{
            smsConfigInfoDao.updateById(smsConfigInfo);
        }
    }

    @Override
    public DataTableJson selectSmsConfigInfoPage(Page page) throws Exception {
        log.info("开始分页查询短信配置信息,page:{}", JSONObject.toJSONString(page));
        DataTableJson json = new DataTableJson();
        List<SmsConfigInfo> list = smsConfigInfoDao.pageQueryByCond(page);
        Integer count = smsConfigInfoDao.pageQueryCountByCond(page);
        json.setiTotalRecords(count);
        json.setAaData(list);
        log.info("分页查询短信配置信息结束,count:{},list.size:{},list:{}",count,list.size(),list.toString());
        return json;
    }

    @Override
    public Integer selectSmsCountByUsedStatus(SmsConfigInfo smsConfigInfo) throws Exception {
        Integer mainCount= (Integer) smsConfigInfoDao.findByStatementCond("selectSmsCountByUsedStatus",smsConfigInfo);
        return mainCount;
    }

    @Override
    public SmsConfigInfo selectSmsInfoByUsedStatus(SmsConfigInfo smsConfigInfo) throws Exception {
        List<SmsConfigInfo> smsConfigInfoList=smsConfigInfoDao.findListByStatementCond("selectSmsInfoByUsedStatus",smsConfigInfo);
        if(smsConfigInfoList!=null&&smsConfigInfoList.size()>0){
            return smsConfigInfoList.get(0);
        }
        return null;
    }

    /**
     * 修改资源文件中的短信配置信息
     * @param smsConfigInfo
     */
    private void updatePropertiesMailConfigInfo(SmsConfigInfo smsConfigInfo){
        PropertiesLoad.putProperties(SysConstants.SMS_URL, smsConfigInfo.getSmsSendUrl());
    }

    @Autowired
    public void setMesProcessTrackDao(SqlSessionTemplate sqlSessionTemplate) {
        this.smsConfigInfoDao = new SimpleGenericDAO<SmsConfigInfo, Integer>(sqlSessionTemplate, SmsConfigInfo.class);
    }
}





