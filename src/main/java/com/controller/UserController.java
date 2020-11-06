package com.controller;

import java.security.Principal;
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
public class UserController {
	@Autowired
	private UserInfoDAO userInfoDAO;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model, Principal principal, HttpSession session) {
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		String username = principal.getName();
		UserInfo userInfo = userInfoDAO.getInfoUserByUsername(username);
		session.setAttribute("userid", userInfo.getUserid());
		model.addAttribute("listAllUser", listAllUser);

		return "Home";
	}

	@RequestMapping(value = "/user/userinfo", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {

		String username = principal.getName();
		UserInfo userInfo = userInfoDAO.getInfoUserByUsername(username);
		userInfo.setPassword(null);
		model.addAttribute("userInfo", userInfo);
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "userInfoPage";
	}

	@RequestMapping(value = "/user/info/{userid}", method = RequestMethod.GET)
	public String userInfo(Model model, @PathVariable Integer userid) {
		UserInfo userInfo = userInfoDAO.getInfoUserByUserid(userid);
		userInfo.setPassword(null);
		model.addAttribute("userInfo", userInfo);
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "userInfoPage";
	}

	@RequestMapping(value = "/user/saveinfo", method = RequestMethod.POST)
	public String saveInfo(Model model, @ModelAttribute UserInfo userInfo, Principal principal) {
		String username = principal.getName();
		if (userInfo.getUsername().equals(username)) {
			userInfoDAO.setSaveInfo(userInfo, username);
			List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
			model.addAttribute("listAllUser", listAllUser);
			return "redirect:/";
		} else {
			model.addAttribute("message", "ban khong co quyen cap nhat du lieu user khac");
			return "userInfoPage";
		}

	}

	@RequestMapping(value = "/user/listalluser", method = RequestMethod.GET)
	public String listAllUser(Model model) {
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);

		return "Home";
	}
	

}
