package com.pizza.model;
public class PizzaOrder {
	private int orderId;
	private String pizzaName;
	private String customerName;
	private String pizzaType;
	private String size;
	private int quantity;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getPizzaName() {
		return pizzaName;
	}
	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPizzaType() {
		return pizzaType;
	}
	public void setPizzaType(String pizzaType) {
		this.pizzaType = pizzaType;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public PizzaOrder(int orderId, String pizzaName, String customerName, String pizzaType, String size, int quantity) {
		super();
		this.orderId = orderId;
		this.pizzaName = pizzaName;
		this.customerName = customerName;
		this.pizzaType = pizzaType;
		this.size = size;
		this.quantity = quantity;
	}

	public PizzaOrder() {
		super();
	}

}
