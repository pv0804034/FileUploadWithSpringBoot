package com.imageupload.service;

import java.sql.Timestamp;
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
	FileUploadService fileUploadService;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Value("${upload-dir}")
	private String pathLocation;
	
	@Override
	public ResponseEntity<?> saveProduct(FormRequest formRequest){
		
		MessageResponse message = new MessageResponse();
		message.setMessage("done");
		Product product = new Product(formRequest.getName(),formRequest.getRate());
		productRepository.save(product);
		
		for(Integer i = 0;i<formRequest.getFiles().size();i++) {
			if(!fileUploadService.isValidFile(formRequest.getFiles().get(i).getOriginalFilename())) {
				return ResponseEntity.badRequest().body(new MessageResponse("invalid formate"));
			}
		}
		
		for(Integer i = 0;i<formRequest.getFiles().size();i++) {
			
			String time = new Timestamp(System.currentTimeMillis()).toString();
			fileUploadService.fileUpload(formRequest.getName() + time , formRequest.getFiles().get(i));
			ImageModel imageModel = new ImageModel(
										formRequest.getName()+time+fileUploadService.getFileExtension(formRequest.getFiles().get(i).getOriginalFilename()),
										formRequest.getFiles().get(i).getContentType(),
										product);
			imageRepository.save(imageModel);
		}
		return ResponseEntity.ok(message);
	}

	@Override
	public ResponseEntity<?> getProducts(Long id) {
		if(!productRepository.existsById(id))return ResponseEntity.badRequest().body(new MessageResponse("product not found"));
		Product product = productRepository.getOne(id);
		
		ProductResponse productResponse = new ProductResponse(product.getName(),product.getRate(),pathLocation);
		
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
