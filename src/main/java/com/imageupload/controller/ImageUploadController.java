package com.imageupload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imageupload.request.FormRequest;
import com.imageupload.service.IImageService;

@RestController
@RequestMapping("/api")
public class ImageUploadController {
	
	@Autowired
	IImageService iImageService;
	
	@PostMapping(path = "/save",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> saveProduct(@ModelAttribute FormRequest formRequest) {
		return iImageService.saveProduct(formRequest);
	}
	
	@GetMapping(path = "/get/{id}")
	public ResponseEntity<?> getProducts(@PathVariable Long id){
		return iImageService.getProducts(id);
	}
	
}
