package br.com.brenohff.later.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brenohff.later.model.LTCategory;

public interface CategoryRepository extends JpaRepository<LTCategory, Long>{

}
