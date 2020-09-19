package br.com.vrs.kanban.issues.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vrs.kanban.issues.model.Issue;
import br.com.vrs.kanban.issues.repository.IssueRepository;

@Service
public class IssueService {

	@Autowired
	IssueRepository taskRepository;
	
	public Issue criarTask(Issue task) {
		//task.setStatus(new TransitionStatus(TransitionStatusEnum.BACKLOG.getIdStatus()));
		return taskRepository.save(task);
	}
	
	public Issue alterarTask(Issue task) {
		return taskRepository.save(task);
	}
	
}
