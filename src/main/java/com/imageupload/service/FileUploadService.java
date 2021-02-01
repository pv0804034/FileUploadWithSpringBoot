package com.imageupload.service;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	@Value("${upload-dir}")
	String pathLocation;
	
	public boolean isValidFile(String fileName) {
		String fileExtension = getFileExtension(fileName);
		return fileExtension.equals(".png") || fileExtension.equals(".jpg");
	}
	
	public String getFileExtension(String fileName) {
		String fileExtension = "";
		boolean ok = false;
		for(int i = 0;i<fileName.length();i++) {
			if(fileName.charAt(i) == '.')ok = true;
			if(ok)fileExtension += fileName.charAt(i);
		}
		return fileExtension.toLowerCase();
	}
	
	public void fileUpload(String name,MultipartFile file){
		String fileExtension = getFileExtension(file.getOriginalFilename());
		try {
			File newFile = new File(pathLocation + name + fileExtension);
			newFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(newFile);
			fout.write(file.getBytes());
			fout.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
