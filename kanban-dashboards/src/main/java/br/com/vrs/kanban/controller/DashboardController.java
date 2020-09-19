package br.com.vrs.kanban.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import br.com.vrs.kanban.model.Dashboard;
import br.com.vrs.kanban.model.Widget;
import br.com.vrs.kanban.repository.DashboardRepository;
import br.com.vrs.kanban.repository.WidgetRepository;

@RestController
@CrossOrigin
public class DashboardController {
	
	@Autowired
	DashboardRepository dashboardRepository;
	
	@Autowired
	WidgetRepository widgetRepository;
	
    @Autowired
    DiscoveryClient discoveryClient;
    
    @GetMapping("/dashboards")
    public Iterable<Dashboard> listAll() {
    	
		Iterable<Dashboard> findAll = dashboardRepository.findAll();
		return findAll;
        
    }
    
    @PostMapping("/dashboards")
    Dashboard createDashboard(@RequestBody Dashboard dashboard) {
    	
    	Dashboard dashSaved = dashboardRepository.save(dashboard);
    	
    	for(Widget w : dashboard.getWidgets()) {
    		w.setDashboard(dashSaved);
    		widgetRepository.save(w);
    	}
    	
		return dashSaved;
		
    }
    
    @GetMapping("/dashboards/{idDash}/widget/{idWidget}/execute")
	/*
	 * @HystrixCommand( fallbackMethod = "getDashFallback",
	 * commandProperties={@HystrixProperty(name=
	 * "execution.isolation.thread.timeoutInMilliseconds",value="10000")})
	 */
    Widget executeWidget(@PathVariable("idDash") long idDash, @PathVariable("idWidget") long idWidget) {
    	
    	Widget widgetRet = null;
    	String dataIssues = "";
    	
    	Optional<Widget> widget = widgetRepository.findById(idWidget);
    	if(widget.isPresent()) {
        	
    		widgetRet = widget.get();
    		
        	List<ServiceInstance> list = discoveryClient.getInstances("kanban-issues");
    		ServiceInstance service2 = list.get(0);
    		URI micro2URI = service2.getUri();
    		
    		HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.APPLICATION_JSON);
    		
    		RestTemplate restTemplate = new RestTemplate();
    		
    		ResponseEntity<String> response = restTemplate.getForEntity(micro2URI +
                    "/executeQuery?query=" + widgetRet.getQuery(), String.class);
    		
    		dataIssues = response.getBody();
    		
    	}
		
    	widgetRet.setData(dataIssues);
    	
    	return widgetRet;
		
    }
    
    private Dashboard getDashFallback(long id) {
    	return new Dashboard();
    }

    
}