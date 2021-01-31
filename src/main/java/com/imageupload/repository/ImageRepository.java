package com.imageupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imageupload.imagemodel.ImageModel;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel,Long>{
	
}
