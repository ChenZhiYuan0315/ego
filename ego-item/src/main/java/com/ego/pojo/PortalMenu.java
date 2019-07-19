package com.ego.pojo;

import java.util.List;
/**
 * 如果菜单是父菜单,当前菜单的数据都封装到这个类中
 */
public class PortalMenu {
 private String u;
 private String n;
 private List<Object> i;
public String getU() {
	return u;
}
public void setU(String u) {
	this.u = u;
}
public String getN() {
	return n;
}
public void setN(String n) {
	this.n = n;
}
public List<Object> getI() {
	return i;
}
public void setI(List<Object> i) {
	this.i = i;
}
 
}
