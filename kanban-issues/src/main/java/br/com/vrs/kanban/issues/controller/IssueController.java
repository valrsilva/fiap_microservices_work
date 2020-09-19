package br.com.vrs.kanban.issues.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vrs.kanban.issues.model.Issue;
import br.com.vrs.kanban.issues.model.TypeEnum;
import br.com.vrs.kanban.issues.repository.IssueRepository;
import br.com.vrs.kanban.issues.service.IssueService;
import br.com.vrs.kanban.issues.utils.DateUtils;

@RestController()
@CrossOrigin
public class IssueController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
	private IssueRepository issueRepository;
 
    @Autowired
   	private IssueService issueService;
    
    @GetMapping("/issues")
    public List<Issue> listAllTasksByParams(@RequestParam(required=false) Long tasktype) {
    	
    	if(tasktype != null) {
    		return (List<Issue>) issueRepository.findByTypeId(tasktype);
    	}else {
    		return (List<Issue>) issueRepository.findAll();
    	}
        
    }
 
    @PostMapping("/issues")
    void gravarTask(@RequestBody Issue task) {
    	issueService.criarTask(task);
    }
    
    @GetMapping("/executeQuery")
    public Iterable<Issue> getCommentsByTaskId(@RequestParam("query") String query) {
    	
    	logger.info("executando a query: " + query);
    	
    	if(query.contains("Task")) {
    		return (List<Issue>) issueRepository.findByTypeId(TypeEnum.TASK.getId());
    	}else if(query.contains("Epic")) {
    		return (List<Issue>) issueRepository.findByTypeId(TypeEnum.EPIC.getId());
    	}else if(query.contains("Story")) {
    		return (List<Issue>) issueRepository.findByTypeId(TypeEnum.STORY.getId());
    	}else {
    		 return issueRepository.findAll();
    	}
       
    }

    @DeleteMapping("/issues/{id}")
    void deleteTask(@PathVariable long id) {
    	issueRepository.deleteById(id);
    }
    
}