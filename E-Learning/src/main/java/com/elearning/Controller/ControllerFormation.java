package com.elearning.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.elearning.Beans.Formation;
import com.elearning.Beans.User;
import com.elearning.Beans.Element;
import com.elearning.DAO.ElementRepository;
import com.elearning.DAO.FormationRepository;
import com.elearning.DAO.UserPrincipale;
import com.elearning.DAO.UserRepository;

@RestController
public class ControllerFormation {

	@Autowired
	FormationRepository formationRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ElementRepository elementRepository;

	@PostMapping("/formation/save")
	public ModelAndView saveFormation(Formation f) {
		String currentEmail = ((UserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getEmail();
		User user = userRepository.findByEmail(currentEmail);
		f.setFormateur(user);
		formationRepository.save(f);
		System.out.println(formationRepository.findAll().size());
		ModelAndView mv = new ModelAndView();
		mv.addObject("formations", formationRepository.findAll());
		mv.setViewName("redirect:/formations");
		return mv;
	}
	
	@GetMapping("/formations")
	public ModelAndView indexFormation() {
		ModelAndView mv = new ModelAndView();
		String currentEmail = ((UserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getEmail();

		User user = userRepository.findByEmail(currentEmail);
		
		System.out.println(user.getRole() + "3");
		if (user.getRole().equals("formateur")) {
			List<Formation> formations = formationRepository.findByFormateur(user);
			mv.addObject("formations", formations);
			mv.setViewName("TableauFormations");
		} else {
			List<Formation> formations = formationRepository.findAll();
			mv.addObject("formations", formations);
			mv.setViewName("TableauAllFormationB");
		}

		return mv;
	}
	
	@GetMapping("/formation/{id_formation}")
	public ModelAndView getOneFormation(@PathVariable("id_formation") long id){
		Formation formation = formationRepository.findByIdFormation(id);
		List<Element> elements = elementRepository.findByFormation(formation);
		User user = userRepository.findByEmail(
				((UserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		ModelAndView mv = new ModelAndView();
		mv.addObject("formation",formation);
		mv.addObject("elements",elements);
		if(user.getRole().equals("formateur"))
			{ mv.setViewName("TableauPlan"); }
		else{ mv.setViewName("TableauPlanB"); }
		return mv;
	}

	@GetMapping("/formation/add")
	public ModelAndView gosaveFormation() {
		User formateur = userRepository.findByEmail(
				((UserPrincipale) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
		ModelAndView mv = new ModelAndView();
		mv.addObject("formations", formationRepository.findByFormateur(formateur));
		mv.setViewName("Add-formation");
		return mv;
	}
//	@GetMapping("/formations")
//	public ModelAndView gosaveTableauFormation() {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("formations", formationRepository.findAll());
//		mv.setViewName("TableauFormations");
//		return mv;
//	}

	@GetMapping("/updateFormation/{id}")
	public ModelAndView updateFormation(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView();
		Formation f = formationRepository.findById(id).get();
		mv.addObject("formation", f);
		mv.setViewName("UpdateFormation");
		return mv;
	}

	@GetMapping("/deleteFormation/{id}")
	public ModelAndView deleteFormation(@PathVariable("id") long id) {
		Formation f = formationRepository.findById(id).get();
		formationRepository.delete(f);
		ModelAndView mv = new ModelAndView();
		mv.addObject("formations", formationRepository.findAll());
		mv.setViewName("redirect:/formations");
		return mv;
	}

}
