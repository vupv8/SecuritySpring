package com.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dao.TaskDAO;
import com.dao.UserInfoDAO;
import com.model.Task;
import com.model.UserInfo;
import com.validator.CustomFileValidator;

@Controller
public class TaskController {
	@Autowired
	private TaskDAO taskDAO;
	@Autowired
	private UserInfoDAO userInfoDAO;
	@Autowired
	private ServletContext context;
	@Autowired
	CustomFileValidator customFileValidator;

	@RequestMapping(value = "/user/listtask", method = RequestMethod.GET)
	public String listTask(Model model) {
		List<Task> listTask = taskDAO.getListTask();
		model.addAttribute("listTask", listTask);

		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "taskPage";
	}

	@RequestMapping(value = "/admin/addtask", method = RequestMethod.GET)
	public String addTaskPage(Model model) {

		model.addAttribute("task", new Task());
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "addTaskPage";
	}

	@RequestMapping(value ="/admin/addtasksave", method = RequestMethod.POST)
	public String saveAddTask(@RequestParam MultipartFile file, Model model, HttpSession session,
			@ModelAttribute Task task, Principal principal) {

		Date dateNow = new Date();
		task.setDatecreated(dateNow);
		task.setParents(0);
		task.setUsername(principal.getName());
		task.setAttachfile(file.getOriginalFilename());
		taskDAO.setAddTask(task);
		this.doUpload(file);
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "redirect:/user/listtask";
	}

	@RequestMapping("/user/addtasksave")
	public String userSaveAddTask(@RequestParam MultipartFile file, @RequestParam int taskid,
			@RequestParam String taskname, Model model, HttpSession session, @ModelAttribute Task task,
			Principal principal, BindingResult bindingResult) {

		Date dateNow = new Date();
		task.setDatecreated(dateNow);
		task.setParents(taskid);
		task.setUsername(principal.getName());
		task.setTaskname(taskname);
		task.setAttachfile(file.getOriginalFilename());
		taskDAO.setAddTask(task);
		this.doUpload(file);
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "redirect:/user/listtask";
	}

	@RequestMapping(value = "/user/download/{fileName:.+}")
	public void downloadFileAsResponse(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) {
		try {
			// getting the path to file
			String dir = context.getRealPath("upload/task");
			Path file = Paths.get(dir, fileName);
			if (!Files.exists(file)) {
				String message = "File you are trying to download doesnot exist on the server.";
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(message.getBytes(Charset.forName("UTF-8")));
				outputStream.close();
				return;
			}
			// getting mimetype from context
			String mimeType = request.getServletContext().getMimeType(file.getFileName().toString());
			if (mimeType == null) {
				// Not able to detect mimetype taking default
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			Files.copy(file, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/user/detail/{taskid}", method = RequestMethod.GET)
	public String detailTask(Model model, @PathVariable("taskid") int taskid) {
		Task task = taskDAO.getTaskByTaskid(taskid);
		model.addAttribute("task", task);
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "detailTaskPage";
	}

	private String doUpload(MultipartFile file) {

		// Root Directory.
		String uploadRootPath = context.getRealPath("upload/task");
		System.out.println("uploadRootPath=" + uploadRootPath);
		File uploadRootDir = new File(uploadRootPath);
		// Create directory if it not exists.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		// Client File Name
		String filename = file.getOriginalFilename();

		System.out.println("Client File Name = " + filename);

		if (filename != null && filename.length() > 0) {
			try {
				// Create the file on server
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + filename);

				// Stream to write data to file in server.
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(file.getBytes());
				stream.flush();
				stream.close();
			} catch (Exception e) {
				System.out.println("Error Write file: " + filename);
			}
		}

		return "Upload Susscess";
	}
}
