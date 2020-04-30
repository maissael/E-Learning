package com.elearning.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.Beans.Formation;
import com.elearning.Beans.User;


public interface FormationRepository extends JpaRepository<Formation, Long>{
	
	public List<Formation> findByFormateur(User user);
	public Formation findByIdFormation(Long id);
}
