package com.wfj.exception.dal.entity;

/**
 * Created by Administrator on 2015/9/28.
 */
public class ZkNodeClassLibraryInfo {
    private String parentName;
    private String nodeName;
    private String nodeDesc;
    private String classPath;
    private String libraryPath;

    public String getParentName() {
        return parentName;
    }

    public ZkNodeClassLibraryInfo setParentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    public String getNodeName() {
        return nodeName;
    }

    public ZkNodeClassLibraryInfo setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public String getNodeDesc() {
        return nodeDesc;
    }

    public ZkNodeClassLibraryInfo setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
        return this;
    }

    public String getClassPath() {
        return classPath;
    }

    public ZkNodeClassLibraryInfo setClassPath(String classPath) {
        this.classPath = classPath;
        return this;
    }

    public String getLibraryPath() {
        return libraryPath;
    }

    public ZkNodeClassLibraryInfo setLibraryPath(String libraryPath) {
        this.libraryPath = libraryPath;
        return this;
    }
}
