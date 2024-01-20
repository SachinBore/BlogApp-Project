package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "CategoryDto Model Information")
public class CategoryDto {

	@Schema(description = "Category Id")
	private Long id;
	
	@Schema(description = "Category Name")
	private String name;
	
	@Schema(description = "Category Description")
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
