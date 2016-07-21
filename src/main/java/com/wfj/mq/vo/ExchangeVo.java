package com.wfj.mq.vo;

public class ExchangeVo {
	private Integer status;  //
    private String exchangeDesc;  //
    private Integer exchangeType;  //
    private String exchangeName;  //
    private String exchangeType_hz;
    private Integer sid;  //

    public String getExchangeType_hz() {
        return exchangeType_hz;
    }

    public void setExchangeType_hz(String exchangeType_hz) {
        this.exchangeType_hz = exchangeType_hz;
    }

    public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getExchangeDesc() {
		return exchangeDesc;
	}
	public void setExchangeDesc(String exchangeDesc) {
		this.exchangeDesc = exchangeDesc;
	}
	public Integer getExchangeType() {
		return exchangeType;
	}
	public void setExchangeType(Integer exchangeType) {
		this.exchangeType = exchangeType;
	}
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
    
}
