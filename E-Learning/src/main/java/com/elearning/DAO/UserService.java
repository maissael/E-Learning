package com.elearning.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elearning.DAO.UserPrincipale;
import com.elearning.DAO.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository user;

	public UserService(UserRepository user) {
		super();
		this.user = user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new UserPrincipale(user.findByUsername(username));
	}
	
}
