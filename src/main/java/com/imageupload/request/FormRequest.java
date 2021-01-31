package com.imageupload.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FormRequest {
	
	private String name;
	
	private String rate;
	
	private  List<MultipartFile> files;
	
	public FormRequest(String name,String rate,List<MultipartFile> files) {
		this.name = name;
		this.rate = rate;
		this.files = files;
	} 
	
	public FormRequest() {}

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

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}


}
