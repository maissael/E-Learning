package com.elearning.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.Beans.Element;
import com.elearning.Beans.Formation;

public interface ElementRepository extends JpaRepository<Element, Long>{

	List<Element> findByFormation(Formation formation);

}
