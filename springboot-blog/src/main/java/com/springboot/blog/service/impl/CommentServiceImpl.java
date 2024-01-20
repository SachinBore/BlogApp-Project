package com.springboot.blog.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {

		Comment comment = mapToEntity(commentDto);

		// retrieve post entity by id
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		// set post to comment entity
		comment.setPost(post);

		// save comment entity to DB
		Comment commentResponse = commentRepository.save(comment);

		return mapToDTO(commentResponse);
	}

	private CommentDto mapToDTO(Comment comment) {
		
		CommentDto commentDto = mapper.map(comment, CommentDto.class);
//		CommentDto commentDto = new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
//		commentDto.setBody(comment.getBody());
		return commentDto;
	}

	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = mapper.map(commentDto, Comment.class);
//		Comment comment = new Comment();
//		// comment.setId(commentDto.getId());
//		comment.setName(commentDto.getName());
//		comment.setEmail(commentDto.getEmail());
//		comment.setBody(commentDto.getBody());
		return comment;
	}

	@Override
	public List<CommentDto> getCommentsByPostId(long postId) {

		// retrieve comments by post id
		List<Comment> comments = commentRepository.findByPostId(postId);

		return comments.stream().map(comment -> mapToDTO(comment)).toList();
	}

	@Override
	public CommentDto getCommentById(long postId, long commentId) {
		// retrieve post by id
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		// retrieve comment by id
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		return mapToDTO(comment);
	}

	@Override
	public CommentDto updateComment(long postId, long commentId, CommentDto commentRequest) {

		// retrieve post by id
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		// retrieve comment by id
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}

		comment.setName(commentRequest.getName());
		comment.setEmail(commentRequest.getEmail());
		comment.setBody(commentRequest.getBody());

		Comment updatedComment = commentRepository.save(comment);

		return mapToDTO(updatedComment);
	}

	@Override
	public void deleteComment(long postId, long commentId) {
		// retrieve post by id
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		// retrieve comment by id
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		
		commentRepository.delete(comment);
	}

}
