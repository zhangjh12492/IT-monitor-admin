package com.wfj.exception.util;

import java.util.List;

/**
 * Created by MaYong on 2015/8/18.
 */
public class DataTableJson {
    private Integer iTotalRecords;
    private Integer iTotalDisplayRecords;
    private Integer sEcho; //这个属性需要原封不动地传回给datatable

    private String startRow;	//hbase中的开始行
    private String pageTurnFlag;   //前一页还是后一页,previous前一页，next 后一页 ,值在PageTurnFlatEnum中获取
    private Boolean hasNext;    //是否有下一页
    private Boolean hasPrevious;    //是否有上一页
    private List aaData;

    public Integer getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(Integer iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
        this.iTotalDisplayRecords = iTotalRecords;
    }

    public Integer getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public Integer getsEcho() {
        return sEcho;
    }

    public void setsEcho(Integer sEcho) {
        this.sEcho = sEcho;
    }

    public List getAaData() {
        return aaData;
    }

    public void setAaData(List aaData) {
        this.aaData = aaData;
    }

	public String getStartRow() {
		return startRow;
	}

	public void setStartRow(String startRow) {
		this.startRow = startRow;
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

    public Boolean getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}
