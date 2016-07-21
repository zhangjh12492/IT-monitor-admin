package com.wfj.exception.common;

import java.io.Serializable;

/**
 * 查询结果对象
 * 
 * @author GL
 * 
 * @param <T>
 *            结果对象类型
 */
public class RequestResult<T> implements Serializable {

	private static final long serialVersionUID = -3853597969740885319L;

	private String resultMsg;// 结果信息
	private T content;// 返回结果对象
	private boolean success = false;// 成功标记

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "RequestResult [resultMsg=" + resultMsg + ", content=" + content
				+ ", success=" + success + "]";
	}

}
