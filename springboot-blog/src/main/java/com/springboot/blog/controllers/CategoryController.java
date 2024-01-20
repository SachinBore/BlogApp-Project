package com.springboot.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(
        name = "CRUD REST APIs for Category Resource"
)
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	// add category REST API
	@Operation(
            summary = "Create Category REST API",
            description = "Create Category REST API is used to create a category"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
	@SecurityRequirement(
			name = "Bear Authentication"
	)
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<CategoryDto> addcategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto savedCategory = categoryService.addcategory(categoryDto);
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}
	
	// get category REST API
	@Operation(
            summary = "Get Category REST API",
            description = "Get Category REST API is used to fetch a particular Category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
	@GetMapping("{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoryId) {
		CategoryDto categoryDto =  categoryService.getCategory(categoryId);
		return ResponseEntity.ok(categoryDto);
	}
	
	// get all categories REST API
	@Operation(
            summary = "Get All Categories REST API",
            description = "Get All Categories REST API is used to fetch all Categories from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCatgories() {
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
	
	// update category REST API
	@Operation(
            summary = "Update Category REST API",
            description = "Update Category REST API is used to update a particular Category into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
	@SecurityRequirement(
			name = "Bear Authentication"
	)
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("id") Long categoryId) {
		return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
	}
	
	// delete category REST API
	@Operation(
            summary = "Delete Category REST API",
            description = "Delete Category REST API is used to delete a particular Category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
	@SecurityRequirement(
			name = "Bear Authentication"
	)
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId) {
		categoryService.deleteCategory(categoryId);
		return ResponseEntity.ok("Category deleted successfully!");
	}
}
