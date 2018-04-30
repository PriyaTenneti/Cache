package com.rest.cache;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String message;
	private Boolean isFromTrue;

	public Boolean getIsFromTrue() {
		return isFromTrue;
	}

	public void setIsFromTrue(Boolean isFromTrue) {
		this.isFromTrue = isFromTrue;
	}

	public Product() {
		super();
	}

	public Product(String id, String message) {
		super();
		this.id = id;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
