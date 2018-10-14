package br.com.brenohff.later.service;

import br.com.brenohff.later.enums.UserType;
import br.com.brenohff.later.model.LTUser;
import br.com.brenohff.later.repository.UserRepository;
import br.com.brenohff.later.service.exceptions.ObjectAlreadyExists;
import br.com.brenohff.later.service.exceptions.ObjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<LTUser> getAll() {
        return repository.findAll();
    }

    public LTUser getUserByID(String id) {
        LTUser user = repository.getUserByID(id);
        if (user != null) {
            return user;
        } else {
            throw new ObjectNotFound("Usuário não encontrado.");
        }
    }

    public void saveUser(LTUser user) {
        try {
            user.setMember_since(new Date());
            user.setUserType(UserType.NORMAL);
            repository.save(user);
        } catch (Exception e) {
            throw new ObjectAlreadyExists("Este usuário já está cadastrado.");
        }
    }

}
