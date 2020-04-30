package com.elearning.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.elearning.DAO.ElementRepository;
import com.elearning.DAO.FormationRepository;
import com.elearning.DAO.UserPrincipale;
import com.elearning.DAO.UserRepository;



@RestController
public class ControllerFollow {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	FormationRepository formationRepository;
	@Autowired
	ElementRepository elementRepository;

	@GetMapping("/follow")
	public ModelAndView suivrePage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("formations",formationRepository.findAll());
		mv.setViewName("formations");
		return mv;
	}
	
	@RequestMapping("/suivre/{id}")
	public ModelAndView suivre(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView();
		String email = null;
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass()
				.isAssignableFrom(com.elearning.DAO.UserPrincipale.class)) {
			email = ((UserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getEmail();
		} else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass()
				.isAssignableFrom(org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser.class)) {
			email = (String) ((DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
					.getAttributes().get("email");
		}
//		if (email != null) {
//			User user = userRepository.findByEmail(email);
//			Element element = elementRepository.findById(id).get();
//			if (user != null) {
//				formation.getUser().add(user);
//			}
//			elementRepository.save(element);
//		}
//		mv.setViewName("index");
		return mv;
	}
	
	
	
	
	

}
