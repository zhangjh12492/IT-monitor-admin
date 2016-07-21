package com.wfj.exception.dal.util;

import java.util.List;

public class LimitPageMap<T>{
	private Integer pageNum;	//当前页码
	private Integer allCount;	//总行数
	private Integer pageCount;	//每页行数
	private Integer allPage;	//总页数
	private List<T> pageContent;	//
	private Integer startLimit;	//数据库开始下标
	public static Integer defalutPageNum=1;	//默认开始页
	public static Integer defaultPageCount=10;	//默认每页行数
	public static Integer defaultStartLimit=0;	//默认开始下标
	private T t;
	
	public LimitPageMap() {
		super();
	}
	
	public LimitPageMap(Integer pageNum, Integer pageCount) {
		super();
		this.pageNum = pageNum;
		this.pageCount = pageCount;
	}



	public LimitPageMap(Integer pageNum, Integer allCount, Integer pageCount) {
		
		this.pageNum = (pageNum==null)?defalutPageNum:pageNum;
		this.allCount = allCount==null?0:allCount;
		this.pageCount = pageCount==null?defaultPageCount:pageCount;
		this.startLimit=(pageNum-1)*pageCount;
		if(allCount%pageCount>0){
			this.allPage=allCount/pageCount+1;
		}else if(allCount%pageCount==0){
			this.allPage=allCount/pageCount;
		}
	}
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getAllCount() {
		return allCount;
	}
	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public List<T> getPageContent() {
		return pageContent;
	}
	public void setPageContent(List<T> pageContent) {
		this.pageContent = pageContent;
	}
	public Integer getStartLimit() {
		return startLimit;
	}
	public void setStartLimit(Integer startLimit) {
		this.startLimit = startLimit;
	}
	public Integer getAllPage() {
		return allPage;
	}
	public void setAllPage(Integer allPage) {
		this.allPage = allPage;
	}
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	
}
