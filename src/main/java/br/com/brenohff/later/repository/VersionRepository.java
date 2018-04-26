package br.com.brenohff.later.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brenohff.later.models.LTVersion;

public interface VersionRepository extends JpaRepository<LTVersion, Date> {

}
