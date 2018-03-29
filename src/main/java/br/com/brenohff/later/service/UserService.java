package br.com.brenohff.later.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brenohff.later.models.LTUser;
import br.com.brenohff.later.repository.UserRepository;
import br.com.brenohff.later.service.exceptions.UserAlreadyExistsException;
import br.com.brenohff.later.service.exceptions.UserNotFound;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public List<LTUser> getAll() {
		return repository.findAll();
	}

	public LTUser getUserByFaceID(String face_id) {
		LTUser user = repository.getUserByFaceID(face_id);
		if (user != null) {
			return user;
		} else {
			throw new UserNotFound("Usuário não encontrado.");
		}
	}

	public void saveUser(LTUser user) {
		try {
			repository.save(user);
		} catch (Exception e) {
			throw new UserAlreadyExistsException("Este usuário já está cadastrado.");
		}

	}

}
