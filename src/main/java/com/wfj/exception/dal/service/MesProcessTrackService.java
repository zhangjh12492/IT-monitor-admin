package com.wfj.exception.dal.service;

import com.wfj.exception.dal.entity.MesProcessTrack;
import com.wfj.exception.util.DataTableJson;
import com.wfj.util.Page;

import java.util.List;

public interface MesProcessTrackService {


    /**
     * 分页查询信息处理轨迹
     *
     * @param page
     * @return DataTableJson
     * @throws Exception
     * @throws
     * @Title: findMesProcessTrackByPage
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author ZJHao
     * @date 2015-8-31 上午10:23:05
     */
    public DataTableJson findMesProcessTrackByPage(Page page) throws Exception;

    /**
     * 添加信息处理轨迹
     *
     * @param mesTrack
     * @return void
     * @throws Exception
     * @throws
     * @Title: addMesProcessTrack
     * @author ZJHao
     * @date 2015-8-31 上午10:22:49
     */
    void addMesProcessTrack(MesProcessTrack mesTrack) throws Exception;

    /**
     * 插入多条信息处理轨迹
     *
     * @param mesProcessTracks
     * @throws Exception
     */
    void addMoreMesProcessTrack(List<MesProcessTrack> mesProcessTracks) throws Exception;

    /**
     * 根据errId查询异常信息处理轨迹列表
     *
     * @return List<MesProcessTrack>
     * @throws Exception
     * @throws
     * @Title: getMesProcessTrackListByErrId
     * @author ZJHao
     * @date 2015-8-31 下午12:38:14
     */
    public DataTableJson getMesProcessTrackListByErrId(Page page) throws Exception;

}
