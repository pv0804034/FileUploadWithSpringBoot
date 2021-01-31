package com.imageupload.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imageupload.imagemodel.ImageModel;
import com.imageupload.imagemodel.Product;
import com.imageupload.repository.ImageRepository;
import com.imageupload.repository.ProductRepository;
import com.imageupload.request.FormRequest;
import com.imageupload.response.MessageResponse;
import com.imageupload.response.ProductResponse;

@Service
public class ImageService implements IImageService {
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Value("${upload-dir}")
	private String pathLocation;
	
	@Override
	public ResponseEntity<?> saveProduct(FormRequest formRequest){
		
		Product product = new Product(formRequest.getName(),formRequest.getRate());
		
		productRepository.save(product);
		for(Integer i = 0;i<formRequest.getFiles().size();i++) {
			String fileExtension = "";
			boolean ok = false;
			String s = formRequest.getFiles().get(i).getOriginalFilename();
			for(int j = 0;j<s.length();j++) {
				if(s.charAt(j) == '.')ok = true;
				if(ok)fileExtension += s.charAt(j);
			}
			fileExtension = fileExtension.toLowerCase();
			if(!fileExtension.equals(".jpg") && !fileExtension.equals(".png")) {
				return ResponseEntity.badRequest().body(new MessageResponse("only support .jpg and .png image formate"));
			}
		}
		for(Integer i = 0;i<formRequest.getFiles().size();i++) {
			String fileName = formRequest.getName();
			fileName += i.toString();
			String fileExtension = "";
			boolean ok = false;
			String s = formRequest.getFiles().get(i).getOriginalFilename();
			for(int j = 0;j<s.length();j++) {
				if(s.charAt(j) == '.')ok = true;
				if(ok)fileExtension += s.charAt(j);
			}
			fileExtension = fileExtension.toLowerCase();
			try {
				ImageModel imageModel = new ImageModel(fileName+fileExtension,formRequest.getFiles().get(i).getContentType(),product);
				imageRepository.save(imageModel);
				File newFile = new File(pathLocation + fileName + fileExtension);
				newFile.createNewFile();
				FileOutputStream fout = new FileOutputStream(newFile);
				fout.write(formRequest.getFiles().get(i).getBytes());
				fout.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return ResponseEntity.ok(new MessageResponse("done"));
	}

	@Override
	public ResponseEntity<?> getProducts(Long id) {
		if(!productRepository.existsById(id))return ResponseEntity.badRequest().body(new MessageResponse("product not found"));
		Product product = productRepository.getOne(id);
		
		ProductResponse productResponse = new ProductResponse(product.getName(),product.getRate());
		
		List<String> array = new ArrayList<>();
		
		for(ImageModel img : product.getImageModel()) {
			try {
				array.add(img.getName());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		productResponse.setImageList(array);
		return ResponseEntity.ok(productResponse);
	}
	
	
}
