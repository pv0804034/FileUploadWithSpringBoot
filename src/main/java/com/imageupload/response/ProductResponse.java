package com.imageupload.response;

import java.util.List;

public class ProductResponse {
	
	private String name;
	
	private String rate;
	
	private List<String> imageList;

	public ProductResponse() {}
	
	public ProductResponse(String name,String rate) {
		this.name = name;
		this.rate = rate;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
	
}
