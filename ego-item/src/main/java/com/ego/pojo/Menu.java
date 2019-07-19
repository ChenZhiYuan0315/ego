package com.ego.pojo;

import java.util.List;

/**
 * 最终 ego-item 返回给 ego-portal 的 jsonp 数据中函数的参数值
 */
public class Menu {
private List<Object> data;

public List<Object> getData() {
	return data;
}

public void setData(List<Object> data) {
	this.data = data;
}

}
