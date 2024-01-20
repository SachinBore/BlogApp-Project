package com.springboot.blog.payload;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Schema(description = "PostDto Model Information")
public class PostDto {

	@Schema(description = "Blog Post Id")
	private Long id;

	@Schema(description = "Blog Post Title")
	// title should not be null or empty
	// title should have at least 2 characters
	@NotEmpty
	@Size(min = 2, message = "Post title should have at least 2 characters")
	private String title;

	@Schema(description = "Blog Post Description")
	// post description should not be null or empty
	// post description should have at least 10 characters
	@NotEmpty
	@Size(min = 10, message = "Post Description should have at least 10 characters")
	private String description;

	@Schema(description = "Blog Post Content")
	// post content should not be null or empty
	@NotEmpty
	private String content;
	private Set<CommentDto> comments;

	@Schema(description = "Blog Post Category")
	private Long categoryId;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
}
