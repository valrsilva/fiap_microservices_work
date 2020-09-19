package br.com.vrs.kanban.issues.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;

	@OneToOne
	private Type type;

	//@OneToMany(mappedBy = "task", fetch=FetchType.LAZY)
	//private List<Transition> transitionHistory = new ArrayList<>();
	
	//@OneToMany(mappedBy = "task", fetch=FetchType.LAZY)
	//private List<TaskComment> commnents = new ArrayList<>();
	
	public Issue() {
	}

	public Issue(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Issue(String name, String description, Issue parentTask, Type taskType, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.description = description;
		this.type = taskType;
		this.startDate = startDate;
		this.endDate = endDate;
		//this.status = new TransitionStatus(TransitionStatusEnum.CREATED.idStatus);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/*
	 * public List<Transition> getTransitionHistory() { return transitionHistory; }
	 * 
	 * public void setTransitionHistory(List<Transition> transitionHistory) {
	 * this.transitionHistory = transitionHistory; }
	 */
	
	/*
	 * public void addTransition(Transition transition) {
	 * transitionHistory.add(transition); transition.setTask(this); }
	 */

	/*
	 * public List<TaskComment> getCommnents() { return commnents; }
	 * 
	 * public void setCommnents(List<TaskComment> commnents) { this.commnents =
	 * commnents; }
	 */

}
