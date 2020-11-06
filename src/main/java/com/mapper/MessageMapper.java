package com.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.model.Message;

public class MessageMapper implements RowMapper<Message> {

	@Override
	public Message mapRow(ResultSet rs, int rowNum) throws SQLException {

		int messageid = rs.getInt("messageid");
		int senderid = rs.getInt("senderid");
		int receiverid = rs.getInt("receiverid");
		int status = rs.getInt("status");
		Date datecreated=rs.getDate("datecreated");
		String messagecontent = rs.getString("messagecontent");

		return new Message(messageid, messagecontent,senderid,receiverid,datecreated,status);
	}
	

}