package com.wfj.exception.quartz;

import com.wfj.exception.dal.entity.MesAllProcessReq;
import com.wfj.exception.dal.service.BusiInfoService;
import com.wfj.exception.dal.service.MesAllProcessService;
import com.wfj.exception.dal.service.MesProcessResultService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/10/8.
 */
public class MesProcessResultQuartz {


    private static final Logger log= org.slf4j.LoggerFactory.getLogger(MesProcessResultQuartz.class);
    @Autowired
    private MesAllProcessService mesAllProcessService;
    @Autowired
    private MesProcessResultService mesProcessResultService;
    @Autowired
    private BusiInfoService busiInfoService;

    /**
     * 根据查询到的MapReduce统计后的异常结果的条数发送短信或者邮件
     */
    public void mesProcessResultSendSms(){
        try {
            List<MesAllProcessReq> mesAllProcessReqList=mesAllProcessService.findAllMesProcessGroupBySysCodeToSms();
            if (mesAllProcessReqList!=null&&mesAllProcessReqList.size()>0){
                log.info("查询到统计后的异常结果数据,开始发送短信或者邮件...");
                for (int i=0;i<mesAllProcessReqList.size();i++){
                    mesProcessResultService.mesCountProcessResult(mesAllProcessReqList.get(i));
                }
                log.info("结束发送短信或者邮件...");
            }
        } catch (Exception e) {
            log.error("根据统计后的异常结果条数发送短信或者邮件异常:{}",e.getMessage());
        }
    }

    /**
     * 1.插入MapReduce统计后的业务信息
     * 2.删除BusiInfoTmp中的数据
     * @throws Exception
     */
    public void insertBusiByReduce() throws Exception{
        log.info("开始插入MapReduce统计后的BusiInfo数据");
        try {
            busiInfoService.insertBusiByReduce();
            log.debug("插入MapReduce统计后的BusiInfo数据结束");

        } catch (Exception e) {
            log.error("插入MapReduce统计后的BusiInfo数据出错:{}",e.getMessage());
        }
        try {
            log.debug("开始删除BusiInfoTmp中的所有数据");
            busiInfoService.deleteAllBusiInfoTmp();
            log.debug("删除BusiInfoTmp数据结束");
        } catch (Exception e) {
            log.error("删除BusiInfoTmp数据出错,{}",e.getMessage());
        }

    }


}
