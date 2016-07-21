package com.wfj.exception.dal.service;

import com.wfj.exception.dal.vo.SmsSendVo;

/**
 * Created by MaYong on 2015/8/28.
 */
public interface SmsService {
    public Boolean sendSms(SmsSendVo vo) throws Exception;

    public Boolean sendSms_2(SmsSendVo vo) throws Exception;
}
