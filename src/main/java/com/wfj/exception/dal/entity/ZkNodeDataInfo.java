package com.wfj.exception.dal.entity;

import java.util.Date;

/**
 * zookeepr中监控的节点的信息
 *
 * @author ZJHao
 * @ClassName: ZkNodeData
 * @date 2015年9月25日 上午11:02:11
 */
public class ZkNodeDataInfo {

    private String id;
    private String nodeDesc;    //系统下实例的描述
    private String nodeIp;        //系统下实例的ip地址
    private Date createdTime;    //创建时间
    private String updateTime;    //zk的更新时间
    private String rootName;    //zk中根节点的名称
    private String parentName;    //此节点的父节点名称
    private String nodeName;    //此节点的名称
    private String nodeData;    //节点中的数据
    private String createdTimeFormat;    //按分钟格式化后的日期
    private Integer nodeCount;  //存活节点的数量
    private String libraryPath;
    private String classPath;

    public ZkNodeDataInfo(){}
    public ZkNodeDataInfo(String parentName){
        this.parentName=parentName;
    }

    public String getNodeDesc() {
        return nodeDesc;
    }

    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }

    public String getNodeIp() {
        return nodeIp;
    }

    public void setNodeIp(String nodeIp) {
        this.nodeIp = nodeIp;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeData() {
        return nodeData;
    }

    public void setNodeData(String nodeData) {
        this.nodeData = nodeData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(Integer nodeCount) {
        this.nodeCount = nodeCount;
    }

    public String getCreatedTimeFormat() {
        return createdTimeFormat;
    }

    public void setCreatedTimeFormat(String createdTimeFormat) {
        this.createdTimeFormat = createdTimeFormat;
    }

    public String getLibraryPath() {
        return libraryPath;
    }

    public ZkNodeDataInfo setLibraryPath(String libraryPath) {
        this.libraryPath = libraryPath;
        return this;
    }

    public String getClassPath() {
        return classPath;
    }

    public ZkNodeDataInfo setClassPath(String classPath) {
        this.classPath = classPath;
        return this;
    }

    @Override
    public String toString() {
        return "ZkNodeData{" +
                "id='" + id + '\'' +
                ", nodeDesc='" + nodeDesc + '\'' +
                ", nodeIp='" + nodeIp + '\'' +
                ", createdTime=" + createdTime +
                ", updateTime='" + updateTime + '\'' +
                ", rootName='" + rootName + '\'' +
                ", parentName='" + parentName + '\'' +
                ", nodeName='" + nodeName + '\'' +
                ", nodeData='" + nodeData + '\'' +
                ", createdTimeFormat='" + createdTimeFormat + '\'' +
                '}';
    }
}
