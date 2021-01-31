package com.imageupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imageupload.imagemodel.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	
	boolean existsById(Long id);
	
}
