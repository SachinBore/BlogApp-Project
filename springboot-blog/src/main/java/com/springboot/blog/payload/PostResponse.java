package com.springboot.blog.payload;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "PostResponse Information")
public class PostResponse {

	@Schema(description = "It contains all the Post(s) Details")
	private List<PostDto> content;
	
	@Schema(description = "It shows the current page number that you are seeing")
	private int pageNo;
	
	@Schema(description = "It shows the current page size, like total posts in that page")
	private int pageSize;
	
	@Schema(description = "It shows the total posts that are there in the response")
	private long totalElements;
	
	@Schema(description = "It shows the total pages that are there in the response")
	private int totalPages;
	
	@Schema(description = "It shows the true or false based on if the current page is the last page in the response or not")
	private boolean last;

	public List<PostDto> getContent() {
		return content;
	}

	public void setContent(List<PostDto> content) {
		this.content = content;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}
}
