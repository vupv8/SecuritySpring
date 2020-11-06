package com.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.TaskDAO;
import com.mapper.TaskMapper;
import com.model.Task;

@Service
@Transactional
public class TaskDAOImpl extends JdbcDaoSupport implements TaskDAO {

	@Autowired
	public TaskDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	@Override
	public List<Task> getListTask() {
		String sql = "Select * "//
				+ " from tasks ";

		TaskMapper mapper = new TaskMapper();
		try {
			List<Task> listTask = this.getJdbcTemplate().query(sql, mapper);
			return listTask;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	@Override
	public void setAddTask(Task task) {
		String sql = "INSERT tasks SET taskname=?, description=?, attachfile=?, datecreated=?, username=?, parents=?";

		Object[] params = new Object[] { task.getTaskname(), 
				task.getDescription(), task.getAttachfile(), task.getDatecreated(), task.getUsername(),
				task.getParents() };
		try {
			this.getJdbcTemplate().update(sql, params);

		} catch (EmptyResultDataAccessException e) {

		}
	}
	
	@Override
	public Task getTaskByTaskid(int taskid) {
		String sql = "Select * "//
				+ " from tasks where taskid=? ";

		Object[] params = new Object[] { taskid };
		TaskMapper mapper = new TaskMapper();
		try {
			Task task = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return task;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
}