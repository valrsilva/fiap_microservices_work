package br.com.vrs.kanban;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;

import br.com.vrs.kanban.model.Dashboard;
import br.com.vrs.kanban.repository.DashboardRepository;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class App {

	public static void main(String[] args) {
		
		SpringApplication.run(App.class, args);

	}

	@Component
	public class ApplicationRunnerBean implements ApplicationRunner {

		@Autowired
		DashboardRepository dashRepository;

		@Override
		public void run(ApplicationArguments arg0) throws Exception {
			
			boolean inserirDados = true;
			
			if(inserirDados) {
				
				dashRepository.save(new Dashboard("Squad Dashboard"));
				dashRepository.findAll().forEach(System.out::println);
				
			}
			

		}

		public Date convertToDateViaInstant(LocalDate dateToConvert) {
			return java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		}
	}

}
