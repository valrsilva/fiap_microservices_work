package br.com.vrs.kanban.issues.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.vrs.kanban.issues.model.Issue;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Long>{
	
	public List<Issue> findByTypeId(long type);
	
}
