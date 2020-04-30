package com.elearning.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.Beans.User;

public interface UserRepository extends JpaRepository<User,String>{
	User findByUsername(String username);
	User findByIdUser(Long id);
	User findByRole(String role);
	User findByEmail(String email);
}
