package com.commons.pojo;

public class EgoStatus {
	//给前台页面的状态码
private int status;
  //定义异常信息
private String excep;
//表itemParam 的列data
private Object data;

private Object msg;

public Object getMsg() {
	return msg;
}

public void setMsg(Object msg) {
	this.msg = msg;
}

public Object getData() {
	return data;
}

public void setData(Object data) {
	this.data = data;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

public String getExcep() {
	return excep;
}

public void setExcep(String excep) {
	this.excep = excep;
}

@Override
public String toString() {
	return "EgoStatus [status=" + status + ", excep=" + excep + ", data=" + data + "]";
}




}
