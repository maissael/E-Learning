package com.elearning.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.elearning.Beans.Formation;
import com.elearning.Beans.User;
import com.elearning.DAO.FormationRepository;
import com.elearning.DAO.UserPrincipale;
import com.elearning.DAO.UserRepository;

@RestController
public class ControllerAuthentification {
	@Autowired
	UserRepository userRepository;
	@Autowired
	FormationRepository formationRepository;

//	@GetMapping("/")
//	public ModelAndView i(Model model) {
//		ModelAndView mv = new ModelAndView();
//		String email = null;
//		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass()
//				.isAssignableFrom(org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser.class)) {
//			email = (String) ((DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
//					.getAttributes().get("email");
//		}
//		if (email != null) {
//			User user = userRepository.findByEmail(email);
//			if (user == null) {
//				String username = (String) ((DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication()
//						.getPrincipal()).getAttributes().get("name");
//				user = new User();
//				user.setUsername(username);
//				user.setEmail(email);
//				user.setRole("Beneficiaire");
//				userRepository.save(user);
//			}
//		}
//		mv.setViewName("index");
//		return mv;
//	}

	@GetMapping("/login")
	public ModelAndView login(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}

	@GetMapping("/signup")
	public ModelAndView signup(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("signup");
		return mv;
	}

	@GetMapping("/homepage")
	public ModelAndView home(Model model) {
		ModelAndView mv = new ModelAndView();
		String currentEmail = ((UserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getEmail();

		User user = userRepository.findByEmail(currentEmail);
		List<Formation> formations = formationRepository.findAll();
		mv.addObject("formations", formations);
		System.out.println(user.getRole());
		if (user.getRole().equals("formateur")) {

			mv.setViewName("TableauAllFormationF");
		} else {
			mv.setViewName("TableauAllFormationB");
		}

		return mv;

	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}

}
