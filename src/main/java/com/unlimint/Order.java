package com.unlimint;

import com.opencsv.bean.CsvBindByPosition;

public class Order {

	@CsvBindByPosition(position = 0)
	private Long orderId;

	@CsvBindByPosition(position = 1)
	private Double amount;

	@CsvBindByPosition(position = 2)
	private String currency;

	@CsvBindByPosition(position = 3)
	private String comment;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", amount=" + amount + ", currency=" + currency + ", comment=" + comment
				+ "]";
	}

}
