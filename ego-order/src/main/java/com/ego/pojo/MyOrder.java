package com.ego.pojo;

import java.util.List;

public class MyOrder {
	private int paymentType;
	private String payment;
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;

public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

@Override
public String toString() {
	return "MyOrder [paymentType=" + paymentType + ", payment=" + payment + ", tbOrderItems=" + orderItems
			+ ", tbOrderShipping=" + orderShipping + "]";
}

}
