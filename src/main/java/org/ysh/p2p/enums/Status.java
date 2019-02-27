package org.ysh.p2p.enums;

public enum Status {

	NORMAL(0),ABNORMAL(-1),DELETED(-2);
	
	private Status(int status){
		this.status = status;
	}
	
	private int status;
	
	public String toString(){
		return new StringBuilder("[status=").append(this.name())
				.append("(").append(this.status).append(")").append("]").toString();
	}
	
	public static Status valueOf(int status){
		switch(status){
		case 0:return NORMAL;
		case -1:return ABNORMAL;
		case -2:return DELETED;
		default:
			throw new RuntimeException("错误的参数");
		}
	}
	
	public int value(){
		return this.status;
	}
}
