package com.microservice.inventory.entity;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "InventoryList")
public class Inventory {
	
	@Id
	private String id;
	
	@NotNull(message = "Item Name is required")
	private String itemName;
	
	@NotNull(message = "Quantity is required")
	private String quantity;
	
	
	@NotNull(message = "Price is required")
	private int price;


	public Inventory(String id, @NotNull(message = "Item Name is required") String itemName,
			@NotNull(message = "Quantity is required") String quantity,
			@NotNull(message = "Price is required") int price) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	
}
