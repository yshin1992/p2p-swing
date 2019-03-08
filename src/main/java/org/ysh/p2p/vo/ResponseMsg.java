package org.ysh.p2p.vo;

public class ResponseMsg<T> {

	public static final int SUCCESS_CODE=200;
	
	public static final int FAILURE_CODE=500;
	
	private T data;
	
	private int code=SUCCESS_CODE;
	
	private String extras;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getExtras() {
		return extras;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}

	public void failure(T data){
		this.code = FAILURE_CODE;
		this.data = data;
	}
	
	public void failure(T data,String extras){
		this.code = FAILURE_CODE;
		this.data = data;
		this.extras = extras;
	}
	
	
	@Override
	public String toString() {
		return "ResponseMsg [data=" + data + ", code=" + code + ", extras="
				+ extras + "]";
	}
	
	
	
	
}
