package com.dao;

import java.util.List;

import com.model.Task;

public interface  TaskDAO {
	public List<Task> getListTask();
	public void setAddTask(Task task);
	public Task getTaskByTaskid(int taskid);
}
