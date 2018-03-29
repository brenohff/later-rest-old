package br.com.brenohff.later.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.brenohff.later.models.LTComment;
import br.com.brenohff.later.service.CommentService;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

	@Autowired
	CommentService service;

	@RequestMapping(value = "/getAll")
	public List<LTComment> getAll() {
		return service.getAllComments();
	}

	@RequestMapping(value = "/getCommentsByEvent")
	public List<LTComment> getCommentsByEvent(@RequestParam(value = "event_id") Long event_id) {
		return service.getCommentsByEvent(event_id);
	}
	
	@RequestMapping(value = "saveComment", method = RequestMethod.POST)
	public void saveComment(@RequestBody LTComment comment) {
		service.saveComment(comment);
	}

}
