package com.imageupload.imagemodel;

import javax.persistence.*;

@Entity
@Table(name="tble_image_table",uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class ImageModel {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	private String type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	private Product product;
	
	public ImageModel() {}
	
	public ImageModel(String name, String type,Product product) {
		this.name = name;
		this.type = type;
		this.product = product;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
