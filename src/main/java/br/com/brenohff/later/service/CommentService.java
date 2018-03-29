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

	public List<LTComment> getCommentsByUser(Long user_id) {
		return repository.getCommentsByUser(user_id);
	}

	public List<LTComment> getCommentsByUserAndEvent(Long user_id, Long event_id) {
		return repository.getCommentsByUserAndEvent(user_id, event_id);
	}

	public void saveComment(LTComment comment) {
		repository.save(comment);
	}

}
