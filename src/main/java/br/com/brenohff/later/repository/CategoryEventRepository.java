package br.com.brenohff.later.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brenohff.later.model.LTCategoryEvent;
import br.com.brenohff.later.model.LTCategoryEventPK;

public interface CategoryEventRepository extends JpaRepository<LTCategoryEvent, LTCategoryEventPK>{

}
