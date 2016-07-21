package com.wfj.exception.dal.util;

public class DataTableParam {
	 /// <summary>
    /// DataTable请求服务器端次数
    /// </summary>       
    private String sEcho ;

    /// <summary>
    /// 过滤文本
    /// </summary>
    private String sSearch ;
    
    private String iTotalRecords;
    
    private String iTotalDisplayRecords;

    /// <summary>
    /// 每页显示的数量
    /// </summary>
    private int iDisplayLength ;

    /// <summary>
    /// 分页时每页跨度数量
    /// </summary>
    private int iDisplayStart ;

    /// <summary>
    /// 列数
    /// </summary>
    private int iColumns ;

    /// <summary>
    /// 排序列的数量
    /// </summary>
    private int iSortingCols ;

    /// <summary>
    /// 逗号分割所有的列
    /// </summary>
    private String sColumns ;

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsSearch() {
		return sSearch;
	}

	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public int getiColumns() {
		return iColumns;
	}

	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}

	public int getiSortingCols() {
		return iSortingCols;
	}

	public void setiSortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}
	
    
}
