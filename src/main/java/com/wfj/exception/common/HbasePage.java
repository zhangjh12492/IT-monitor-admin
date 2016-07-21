package com.wfj.exception.common;

/**
 * hbase查询的分页对象
 * @ClassName: HbasePage
 * @author ZJHao
 * @date 2015年9月23日 下午9:24:03
 *
 */
public class HbasePage<T> {

	private String startRow;	//hbase的开始行
	private Integer pageSize;	//hbase查询的每页条数

	private String pageTurnFlag;   //前一页还是后一页,previous前一页，next 后一页 ,值在PageTurnFlatEnum中获取
	private Boolean hasPrevious;    //是否有上一页
	private Boolean hasNext;	//是否有下一页
	private T object;
	
	public HbasePage(){
		this.pageSize=11;	//每页条数默11条
	}
	
	public HbasePage(T t){
		this.pageSize=11;
		this.object=t;
	}

	public String getStartRow() {
		return startRow;
	}

	public void setStartRow(String startRow) {
		this.startRow = startRow;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	public Boolean getHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(Boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public String getPageTurnFlag() {
		return pageTurnFlag;
	}

	public void setPageTurnFlag(String pageTurnFlag) {
		this.pageTurnFlag = pageTurnFlag;
	}

	public Boolean getHasNext() {
		return hasNext;
	}

	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}

	@Override
	public String toString() {
		return "HbasePage{" +
				"startRow='" + startRow + '\'' +
				", pageSize=" + pageSize +
				", pageTurnFlag='" + pageTurnFlag + '\'' +
				", hasPrevious=" + hasPrevious +
				", object=" + object +
				'}';
	}
}
