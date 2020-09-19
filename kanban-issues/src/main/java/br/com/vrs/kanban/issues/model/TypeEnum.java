package br.com.vrs.kanban.issues.model;

public enum TypeEnum{
	
	TASK(1),
	STORY(2),
	EPIC(3),
	FEATURE(4),
	RELEASE(5),
	SUBTASK(6),
	BUG(7);
	
	long id = 0;
	
	private TypeEnum(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
}