package com.elearning.Controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.elearning.Beans.User;
import com.elearning.DAO.UserPrincipale;
import com.elearning.DAO.UserRepository;


@RestController
public class ControllerUser {

	@Autowired
	UserRepository userRepository;

	final String username = "e-learning@gmail.com";
	final String password = "e-learning";

	private void sendEmail(String email, String object, String text) throws Exception {

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("e-learning@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject(object);
			message.setText(text);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public String currentUserEmail() {
		return ((UserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
	}

	@PostMapping("/register")
	public ModelAndView addUser(@Valid @ModelAttribute("User") User user) {
		ModelAndView mv = new ModelAndView();
		PasswordEncoder passwordEnocder = new BCryptPasswordEncoder();
		user.setPassword(passwordEnocder.encode(user.getPassword()));
		userRepository.save(user);
		mv.setViewName("login");
		return mv;
		// if role = formateur setview(formateur.html)
		// if role = beneficiaire setview(benef.html)
	}

	@PostMapping("/updateUser")
	public ModelAndView updateUser(@Valid @ModelAttribute("User") User user) {
		ModelAndView mv = new ModelAndView();
		User userDatabase = userRepository.findByEmail(this.currentUserEmail());
		if (userDatabase != null) {
			if (!user.getEmail().isEmpty()) {
				userDatabase.setEmail(user.getEmail());
			}
			if (!user.getUsername().isEmpty()) {
				userDatabase.setUsername(user.getUsername());
			}
			userRepository.save(userDatabase);
			mv.setViewName("index");
		} else {
			mv.setViewName("Error");
		}
		return mv;
	}

	@GetMapping("/updateUser")
	public ModelAndView updateUserPage() {
		ModelAndView mv = new ModelAndView();
		String email = this.currentUserEmail();
		String username = ((UserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getUsername();

		mv.addObject("email", email);
		mv.addObject("username", username);

		mv.setViewName("updateUser");
		return mv;
	}

	@GetMapping("/profil")
	public ModelAndView profilPage() {
		ModelAndView mv = new ModelAndView();
		User user = userRepository.findByEmail(
				((UserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		mv.addObject("user", user);
		mv.setViewName("Profil");
		System.out.println(user);
		return mv;
	}

	@PostMapping("/profil")
	public ModelAndView changePassword(@Valid @ModelAttribute("User") User user,
			@RequestParam("oldPassword") String oldPass, @RequestParam("newPassword") String newPass) {
		ModelAndView mv = new ModelAndView();
		User userDatabase = userRepository.findByEmail(this.currentUserEmail());
		if (userDatabase != null) {
			if (!user.getEmail().isEmpty()) {
				userDatabase.setEmail(user.getEmail());
			}
			if (!user.getUsername().isEmpty()) {
				userDatabase.setUsername(user.getUsername());
			}
		}
		PasswordEncoder passwordEnocder = new BCryptPasswordEncoder();
		if (passwordEnocder.matches(oldPass, userDatabase.getPassword())) {
			userDatabase.setPassword(passwordEnocder.encode(newPass));
			userRepository.save(userDatabase);
			mv.setViewName("index");
			return mv;
		}
		User user1 = userRepository.findByEmail(
				((UserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		mv.addObject("user", user1);
		mv.setViewName("profil");
		return mv;
	}

}
