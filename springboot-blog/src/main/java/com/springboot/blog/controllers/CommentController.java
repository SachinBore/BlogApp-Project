package com.springboot.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Tag(
        name = "CRUD REST APIs for Comment Resource"
)
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Operation(
            summary = "Create Comment REST API",
            description = "Create Comment REST API is used to create comment for a particular post"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId,@Valid @RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}
	
	@Operation(
            summary = "Get All Comments of a Post REST API",
            description = "Get All Comments of a Post REST API is used to fetch all the comments of a particular post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable("postId") long postId) {
		return commentService.getCommentsByPostId(postId);
	}
	
	@Operation(
            summary = "Get a Comment of a Post REST API",
            description = "Get a Comment of a Post REST API is used to fetch a particular comment of a post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
	@GetMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") long postId,@PathVariable("id") long commentId) {
		return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
	}
	
	@Operation(
            summary = "Update Comment of a Post REST API",
            description = "Update Comment of a Post REST API is used to update a particular comment of a post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
	@PutMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") long postId, @PathVariable("id") long commentId,@Valid @RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
	}
	
	@Operation(
            summary = "Delete Comment of a Post REST API",
            description = "Delete Comment of a Post REST API is used to delete a particular comment of a post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
	@DeleteMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId, @PathVariable("id") long commentId) {
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<>("Comment Deleted Sucessfully", HttpStatus.OK);
	}
}
