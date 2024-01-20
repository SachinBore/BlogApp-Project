package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Schema(description = "CommentDto Model Information")
public class CommentDto {
	
	@Schema(description = "Comment Id")
	private Long id;

	// name should not be null or empty
	@Schema(description = "Name of the person who wants to comment")
	@NotEmpty(message = "Name should not be null or empty")
	private String name;

	// email should not be null or empty
	// email field validation
	@Schema(description = "Email of the person who wants to comment")
	@NotEmpty(message = "Email	 should not be null or empty")
	@Email
	private String email;

	// comment body should not be null or empty
	// comment body must be minimum 10 characters
	@Schema(description = "Content of the comment")
	@NotEmpty
	@Size(min = 10, message = "Comment body must be minimum 10 characters")
	private String body;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
