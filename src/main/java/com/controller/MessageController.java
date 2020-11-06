package com.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.MessageDAO;
import com.dao.UserInfoDAO;
import com.model.Message;
import com.model.UserInfo;

@Controller
public class MessageController {
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private UserInfoDAO userInfoDAO;

	@RequestMapping(value = "/user/message/{userid}", method = RequestMethod.GET)
	public String adminPage(Model model, @PathVariable Integer userid, HttpSession session) {
		//
		Integer receiverid = (Integer) session.getAttribute("userid");
		List<Message> ListMessage = messageDAO.getMessage(userid, receiverid);
		model.addAttribute("ListMessage", ListMessage);
		model.addAttribute("messages", new Message());
		model.addAttribute("userid", userid);
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		session.setAttribute("receiverid", userid);
		return "messagePage";
	}

	@RequestMapping(value = "/user/addmessage", method = RequestMethod.POST)
	public String saveAddMessage(Model model, @ModelAttribute Message messages, HttpSession session) {
		if(messages.getMessageid()==0)
		{
			Date dateNow = new Date();
			messages.setSenderid((Integer) session.getAttribute("userid"));
			messages.setDatecreated(dateNow);
			messages.setStatus(1);
			messageDAO.setAddMessage(messages);
		}
		else {
			
			messages.setStatus(3);
			messageDAO.setEditMessage(messages);
		}
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		
		return "redirect:/user/message/" +session.getAttribute("receiverid");
	}

	@RequestMapping(value = "/user/delmessage/{messageid}", method = RequestMethod.GET)
	public String delMessage(Model model, @PathVariable Integer messageid, HttpSession session) {
		Message message= messageDAO.getMessageByMessageid(messageid);
		if(message==null)
		{
			model.addAttribute("messages", new Message());
		}else
		{
			messageDAO.setDelMessage(messageid);
		}
		return "redirect:/user/message/" +session.getAttribute("receiverid");
	}
	@RequestMapping(value = "/user/editmessage/{messageid}", method = RequestMethod.GET)
	public String editMessage(Model model, @PathVariable Integer messageid, HttpSession session) {
		Message message= messageDAO.getMessageByMessageid(messageid);
		if(message==null)
		{
			model.addAttribute("messages", new Message());
		}else
		{
			model.addAttribute("messages", message);
		}
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "redirect:/user/message/" +session.getAttribute("receiverid");
	}
	
}
