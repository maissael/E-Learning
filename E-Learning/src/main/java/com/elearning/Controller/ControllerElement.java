package com.elearning.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.elearning.Beans.Element;
import com.elearning.Beans.Formation;
import com.elearning.Beans.User;
import com.elearning.DAO.ElementRepository;
import com.elearning.DAO.FormationRepository;
import com.elearning.DAO.UserPrincipale;
import com.elearning.DAO.UserRepository;

@RestController
public class ControllerElement {

	@Autowired
	ElementRepository elementRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	FormationRepository formationRepository;

	
	@PostMapping("/element/save")
	public ModelAndView saveElement(Element e){
//     	Formation formation = formationRepository.findByIdFormation(id);
		System.out.println(e.getFormation());
		elementRepository.save(e);
		ModelAndView mv =new ModelAndView();
		mv.addObject("elements",elementRepository.findAll());
		mv.setViewName("redirect:/formations");
		return mv;
	}
	
	public ModelAndView indexFormation(){
		ModelAndView mv =new ModelAndView();
		mv.addObject("elements",elementRepository.findAll());
		mv.setViewName("TableauFormations");
		return mv;
	}
	
	@GetMapping("/element/add")
	public ModelAndView gosaveElement(){
		ModelAndView mv =new ModelAndView();
		mv.addObject("elements",elementRepository.findAll());
		mv.setViewName("add-formation");
		return mv;
	}
	
	@GetMapping("/updateElement/{id}")
	public ModelAndView updateElement(@PathVariable("id") long id){
		ModelAndView mv =new ModelAndView();
		Element e= elementRepository.findById(id).get();
		mv.addObject("element",e);
		mv.setViewName("updateElement");
		return mv;
	}
	
	@GetMapping("/deleteElement/{id}") 
	public ModelAndView deleteElement(@PathVariable("id") long id){ 
		Element e= elementRepository.findById(id).get();
		elementRepository.delete(e);
		ModelAndView mv =new ModelAndView();
		mv.addObject("elements",elementRepository.findAll());
		mv.setViewName("TableauFormations");
		return mv;
	}
}
