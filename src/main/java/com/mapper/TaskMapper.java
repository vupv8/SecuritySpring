package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.model.Task;

public class TaskMapper implements RowMapper<Task> {
	
	@Override
	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {

		
		int taskid = rs.getInt("taskid");
		String taskname = rs.getString("taskname");
		String description = rs.getString("description");
		String attachfile = rs.getString("attachfile");
		Date datecreated = rs.getDate("datecreated");
		String username = rs.getString("username");
		int parents = rs.getInt("parents");

		return new Task( taskid,  taskname,  description,  attachfile,  datecreated,  username,
				 parents);
	}
}
