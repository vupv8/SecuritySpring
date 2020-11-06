package com.model;

import java.util.Date;

public class Message {
							
	private int messageid;
    private String messagecontent;
    private int senderid;
    private int receiverid;
    private Date datecreated;
    private int status;

     
    public Message()  {
         
    }
 
    
    public Message(int messageid ,String messagecontent, int senderid,int receiverid, Date datecreated,int status) {
    	this.messageid = messageid;
    	this.messagecontent = messagecontent;
        this.senderid = senderid;
        this.receiverid = receiverid;
    	this.datecreated = datecreated;
        this.status = status;
    }


	public int getMessageid() {
		return messageid;
	}


	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}


	public String getMessagecontent() {
		return messagecontent;
	}


	public void setMessagecontent(String messagecontent) {
		this.messagecontent = messagecontent;
	}


	public int getSenderid() {
		return senderid;
	}


	public void setSenderid(int senderid) {
		this.senderid = senderid;
	}


	public int getReceiverid() {
		return receiverid;
	}


	public void setReceiverid(int receiverid) {
		this.receiverid = receiverid;
	}


	public Date getDatecreated() {
		return datecreated;
	}


	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}

	
 
}