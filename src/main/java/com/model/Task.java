package com.model;

import java.util.Date;

public class Task {

	private int taskid;
	private String taskname;
	private String description;
	private String attachfile;
	private Date datecreated;
	private String username;
	private int parents;

	public Task() {

	}

	public Task(int taskid, String taskname, String description, String attachfile, Date datecreated, String username,
			int parents) {
		this.taskid = taskid;
		this.taskname = taskname;
		this.description = description;
		this.attachfile = attachfile;
		this.datecreated = datecreated;
		this.username = username;
		this.parents = parents;
	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttachfile() {
		return attachfile;
	}

	public void setAttachfile(String attachfile) {
		this.attachfile = attachfile;
	}

	public Date getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getParents() {
		return parents;
	}

	public void setParents(int parents) {
		this.parents = parents;
	}

	
}
