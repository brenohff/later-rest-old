package br.com.brenohff.later.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brenohff.later.models.LTComment;
import br.com.brenohff.later.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	CommentRepository repository;

	public List<LTComment> getAllComments() {
		return repository.findAll();
	}

	public List<LTComment> getCommentsByEvent(Long event_id) {
		return repository.getCommentsByEvent(event_id);
	}

	public void saveComment(LTComment comment) {
		repository.save(comment);
	}

}
