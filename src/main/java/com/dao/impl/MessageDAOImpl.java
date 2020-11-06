package com.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MessageDAO;
import com.mapper.MessageMapper;
import com.model.Message;

@Service
@Transactional
public class MessageDAOImpl extends JdbcDaoSupport implements MessageDAO {

	@Autowired
	public MessageDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	@Override
	public Message getMessageByMessageid(int messageid) {
		String sql = "Select * "//
				+ " from messages where messageid=? ";

		Object[] params = new Object[] { messageid };
		MessageMapper mapper = new MessageMapper();
		try {
			Message message = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return message;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	@Override
	public List<Message> getMessage(Integer senderid, Integer receiverid) {
		String sql = "Select * "//
				+ " from Messages where senderid = ? or receiverid=? or senderid = ? or receiverid=?";
		System.out.print(senderid);
		System.out.print(receiverid);
		Object[] params = new Object[] { senderid, receiverid, receiverid, senderid };
		MessageMapper mapper = new MessageMapper();
		try {
			List<Message> message = this.getJdbcTemplate().query(sql, params, mapper);
			return message;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void setAddMessage(Message message) {


		String sql = "INSERT messages SET messagecontent=?, senderid=?, receiverid=?, datecreated=?, status=?";

		Object[] params = new Object[] { message.getMessagecontent(), message.getSenderid(), message.getReceiverid(), message.getDatecreated(), message.getStatus(), };
		try {
			this.getJdbcTemplate().update(sql, params);

		} catch (EmptyResultDataAccessException e) {

		}
	}
	@Override
	public void setDelMessage(int messageid) {
		String sql = "UPDATE messages SET status=? WHERE messageid=?";
		Object[] params = new Object[] {2,messageid };
		try {
			this.getJdbcTemplate().update(sql, params);

		} catch (EmptyResultDataAccessException e) {

		}

	}
	@Override
	public void setEditMessage(Message message) {


		String sql = "UPDATE messages SET messagecontent=?,status=? WHERE messageid=?";
		Object[] params = new Object[] {message.getMessagecontent(),message.getStatus(),message.getMessageid() };
		try {
			this.getJdbcTemplate().update(sql, params);

		} catch (EmptyResultDataAccessException e) {

		}
	}

}