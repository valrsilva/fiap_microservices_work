package br.com.vrs.kanban.issues;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import br.com.vrs.kanban.issues.model.Issue;
import br.com.vrs.kanban.issues.model.Type;
import br.com.vrs.kanban.issues.model.TypeEnum;
import br.com.vrs.kanban.issues.repository.IssueRepository;
import br.com.vrs.kanban.issues.repository.TypeRepository;
import br.com.vrs.kanban.issues.service.IssueService;

@SpringBootApplication
public class IssuesApp {

	public static void main(String[] args) {

		SpringApplication.run(IssuesApp.class, args);

	}

	@Component
	public class ApplicationRunnerBean implements ApplicationRunner {

		@Autowired
		TypeRepository taskTypeRepository;
		
		@Autowired
		IssueRepository taskRepository;
		
		@Autowired
		IssueService taskService;
		
		@Autowired
		TypeRepository typeRepository;

		@Override
		public void run(ApplicationArguments arg0) throws Exception {

			boolean inserirDados = true;
			
			if(inserirDados) {
							
				typeRepository.save(new Type("TASK", "TASK", ""));
				typeRepository.save(new Type("STORY", "STORY", ""));
				typeRepository.save(new Type("EPIC", "EPIC", ""));
				
				taskService.criarTask(new Issue("Tarefa 1" ,"Tarefa 1" , null, new Type(TypeEnum.TASK.getId()),
						convertToDateViaInstant(LocalDate.of(2019, Month.NOVEMBER, 30)),
						convertToDateViaInstant(LocalDate.of(2020, Month.JANUARY, 13))));
				
				taskService.criarTask(new Issue("Tarefa 2" ,"Tarefa 2", null, new Type(TypeEnum.STORY.getId()),
						convertToDateViaInstant(LocalDate.of(2019, Month.JUNE, 10)),
						convertToDateViaInstant(LocalDate.of(2019, Month.OCTOBER, 21))));
				
				taskService.criarTask(new Issue("Tarefa 3" ,"Tarefa 3", null, new Type(TypeEnum.EPIC.getId()),
						convertToDateViaInstant(LocalDate.of(2019, Month.JULY, 16)),
						convertToDateViaInstant(LocalDate.of(2019, Month.OCTOBER, 18))));
				
				taskRepository.findAll().forEach(System.out::println);
				
			}
			

		}

		public Date convertToDateViaInstant(LocalDate dateToConvert) {
			return java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		}
	}

}
