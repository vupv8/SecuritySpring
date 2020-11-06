package com.dao;

import java.util.List;

import com.model.Message;

public interface MessageDAO {
	public Message getMessageByMessageid(int messageid);
	public List<Message> getMessage(Integer senderid,Integer receiverid);
	public void setAddMessage(Message message);
	public void setDelMessage(int messageid);
	public void setEditMessage(Message message);
}