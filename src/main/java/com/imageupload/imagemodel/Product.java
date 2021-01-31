package com.imageupload.imagemodel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="tbl_product",uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Product {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;

	
	@Column(name="rate")
	private String rate;
	
	public Product(String name, String rate) {
		this.name = name;
		this.rate = rate;
	}

	public Product() {}
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
	private Set<ImageModel> imageModel = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<ImageModel> getImageModel() {
		return imageModel;
	}

	public void setImageModel(Set<ImageModel> imageModel) {
		this.imageModel = imageModel;
	}

}
