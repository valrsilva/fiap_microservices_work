package br.com.vrs.kanban.issues.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.vrs.kanban.issues.model.Type;

@Repository
public interface TypeRepository extends CrudRepository<Type, Long>{}
