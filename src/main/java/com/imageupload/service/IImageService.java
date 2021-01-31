package com.imageupload.service;

import org.springframework.http.ResponseEntity;

import com.imageupload.request.FormRequest;

public interface IImageService {
	
	ResponseEntity<?> saveProduct(FormRequest formRequest);
	
	ResponseEntity<?> getProducts(Long id);
	
}	
