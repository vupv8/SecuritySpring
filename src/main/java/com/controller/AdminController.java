package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.UserInfoDAO;
import com.model.UserInfo;
@Controller
public class AdminController {
	@Autowired
	private UserInfoDAO userInfoDAO;
	@RequestMapping(value = "/admin/listallinfouser", method = RequestMethod.GET)
	public String listAllInfoUser(Model model) {
		List<UserInfo> listAllInfoUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllInfoUser", listAllInfoUser);
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "userManagementPage";
	}

	@RequestMapping(value = "/admin/edituser/{userid}", method = RequestMethod.GET)
	public String editUserPage(Model model, @PathVariable Integer userid, HttpSession session) {
		session.setAttribute("useridedit", userid);
		UserInfo infoUser = userInfoDAO.getInfoUserByUserid(userid);
		infoUser.setPassword(null);
		model.addAttribute("infoUser", infoUser);
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "editUserPage";
	}

	@RequestMapping(value = "/admin/editsave", method = RequestMethod.POST)
	public String editSaveUser(Model model, @ModelAttribute UserInfo userInfo, HttpSession session) {
		Integer userid = (Integer) session.getAttribute("useridedit");
		userInfoDAO.setUpdateUser(userInfo, userid);
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "redirect:/admin/listallinfouser";
	}

	@RequestMapping(value = "/admin/adduser", method = RequestMethod.GET)
	public String addUserPage(Model model) {

		model.addAttribute("infoUser", new UserInfo());
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "addUserPage";
	}

	@RequestMapping(value = "/admin/addsave", method = RequestMethod.POST)
	public String addSaveUser(Model model, @ModelAttribute UserInfo userInfo) {

		userInfoDAO.setAddUser(userInfo);
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "redirect:/admin/listallinfouser";
	}

	@RequestMapping(value = "/admin/deluser/{userid}", method = RequestMethod.POST)
	public String delUser(Model model, @PathVariable Integer userid) {
		userInfoDAO.setDelUser(userid);

		return "redirect:/admin/listallinfouser";
	}
}
