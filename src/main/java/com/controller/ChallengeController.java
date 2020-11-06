package com.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

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

import com.dao.ChallengeDAO;
import com.dao.UserInfoDAO;
import com.model.Challenge;
import com.model.UserInfo;
import com.validator.CustomFileValidator;

@Controller
public class ChallengeController {
	@Autowired
	private UserInfoDAO userInfoDAO;
	@Autowired
	private ChallengeDAO challengeDAO;
	@Autowired
	private ServletContext context;
	private CustomFileValidator customFileValidator;
	public static final String MIME_TYPE = "text/plain";
	public static final String FILE_EXTENTION = ".TXT";
	public static final int FILE_NAME_LENGHT = 200;
	public static final long TEN_MB_IN_BYTES = 1048576;

	@RequestMapping(value = "/user/listchallenge", method = RequestMethod.GET)
	public String challengePage(Model model) {
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		List<Challenge> listChallenge = challengeDAO.getChallenge();
		model.addAttribute("listChallenge", listChallenge);

		return "challengePage";
	}

	@RequestMapping(value = "/admin/addchallenge", method = RequestMethod.GET)
	public String addChallengePage(Model model) {

		model.addAttribute("challenge", new Challenge());
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "addChallengePage";
	}

	@RequestMapping("admin/addchallenge")
	public String saveAddChallenge(@RequestParam MultipartFile file, Model model, @ModelAttribute Challenge challenge) {

		if (file.isEmpty()) {
			model.addAttribute("message", "File can not be empty1");
			return "addChallengePage";
		} else if (!MIME_TYPE.equalsIgnoreCase(file.getContentType())) {
			model.addAttribute("message", "File can not be empty2");
			return "addChallengePage";
		}else if (file.getSize() > TEN_MB_IN_BYTES) {
			model.addAttribute("message", "File can not be empty3");
			return "addChallengePage";
		} else if (file.getName().toUpperCase().endsWith(FILE_EXTENTION)) {
			model.addAttribute("message", "File can not be empty4");
			return "addChallengePage";
		} else if (file.getName().toUpperCase().length() >= FILE_NAME_LENGHT) {
			model.addAttribute("message", "File can not be empty5");
			return "addChallengePage";
		}else if (file.getName().toUpperCase().matches(".*[^\\w\\s]") ) {
			model.addAttribute("message", "File name phai la tieng viet khong dau ngan cach bang khoang trang");
			return "addChallengePage";
		}
		challenge.setChallengename(challenge.getChallengename());
		challenge.setSuggest(challenge.getSuggest());
		challengeDAO.setAddChallenge(challenge);
		this.doUpload(file, challenge.getChallengename());
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "redirect:/user/listchallenge";
	}

	@RequestMapping(value = "/user/detailchallenge/{challengeid}", method = RequestMethod.GET)
	public String detailChallenge(Model model, @PathVariable("challengeid") int challengeid) {
		Challenge challenge = challengeDAO.getChallengeByChallengeid(challengeid);
		model.addAttribute("challenge", challenge);
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "detailChallengePage";
	}

	@RequestMapping(value = "/user/submitanswer/{challengename}", method = RequestMethod.POST)
	public String submitAnswer(Model model, @PathVariable("challengename") String challengename,
			@RequestParam String answer) {
		String everything;
		try {
			// getting the path to file
			String dir = context.getRealPath("upload/challenge/" + challengename + "/");

			if (answer.contains(".txt")) {
				Path file = Paths.get(dir, answer);

				if (Files.exists(file)) {
					BufferedReader br = new BufferedReader(new FileReader(file.toString()));
					try {
						StringBuilder sb = new StringBuilder();
						String line = br.readLine();

						while (line != null) {
							sb.append(line);
							sb.append(System.lineSeparator());
							line = br.readLine();
						}
						everything = sb.toString();
						model.addAttribute("message", everything);

					} finally {
						br.close();
					}
				}
			} else {
				Path file = Paths.get(dir, answer + ".txt");

				if (Files.exists(file)) {
					BufferedReader br = new BufferedReader(new FileReader(file.toString()));
					try {
						StringBuilder sb = new StringBuilder();
						String line = br.readLine();

						while (line != null) {
							sb.append(line);
							sb.append(System.lineSeparator());
							line = br.readLine();
						}
						everything = sb.toString();
						model.addAttribute("message", everything);
					} finally {
						br.close();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<UserInfo> listAllUser = userInfoDAO.getAllInfoUser();
		model.addAttribute("listAllUser", listAllUser);
		return "detailChallengePage";

	}

	private String doUpload(MultipartFile file, String Challengename) {

		// Root Directory.
		String uploadRootPath = context.getRealPath("upload/challenge/" + Challengename);
		System.out.println("uploadRootPath=" + uploadRootPath);
		File uploadRootDir = new File(uploadRootPath);
		// Create directory if it not exists.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		// Client File Name
		String filename = removeAccent(file.getOriginalFilename());;

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
	private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự',};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u',};

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }
}
