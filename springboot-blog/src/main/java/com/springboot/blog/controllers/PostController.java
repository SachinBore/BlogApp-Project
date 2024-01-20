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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {
	
	@Autowired
	private PostService postService;
	
	// create blog post rest api
	@Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used create a post"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
	@SecurityRequirement(
			name = "Bear Authentication"
	)
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/api/v1/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	
	
	// get all posts rest api
	@Operation(
            summary = "Get All Posts REST API",
            description = "Get All Posts REST API is used to fetch all the posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
	@GetMapping("/api/v1/posts")
	public PostResponse getAllPosts(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
	) {
		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}
	
	
	// get post by id
	@Operation(
            summary = "Get Post By Id REST API",
            description = "Get Post By Id REST API is used to get single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
	@GetMapping("/api/v1/posts/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	// update post by id
	@Operation(
            summary = "update Post REST API",
            description = "Update Post REST API is used to update a particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
	@SecurityRequirement(
			name = "Bear Authentication"
	)
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/api/v1/posts/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("id") long id) {
		
		PostDto postResponse = postService.updatePost(postDto, id);
		return ResponseEntity.ok(postResponse);
	}
	
	// delete post
	@Operation(
            summary = "Delete Post REST API",
            description = "Delete Post REST API is used to delete a particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
	@SecurityRequirement(
			name = "Bear Authentication"
	)
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/api/v1/posts/{id}")
	public ResponseEntity<String> deletePost(@PathVariable("id") long id) {
		postService.deletePostById(id);
		return ResponseEntity.ok("Post Deleted Successfully");
	}
	
	// get posts by category id
	@Operation(
            summary = "Get Posts By Category REST API",
            description = "Get Posts By Category REST API is used to fetch all the posts of a particular category"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
	@GetMapping("/api/v1/posts/category/{id}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryId) {
		List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
		return ResponseEntity.ok(postDtos);
	}
}
