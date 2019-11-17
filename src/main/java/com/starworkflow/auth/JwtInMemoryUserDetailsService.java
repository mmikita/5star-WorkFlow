package com.starworkflow.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.starworkflow.model.User;
import com.starworkflow.repository.UserRepository;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {
	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
	@Autowired
	UserRepository userrepo;
	


	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> allUsers = userrepo.getAllUsers();
		//System.out.print("-----------+"+allUsers.size()+"------------------");
		//userrepo.addUser();
		for(User user : allUsers) {
			inMemoryUserList.add(new JwtUserDetails(user.getId(), user.getLogin(), user.getPassword(), user.getRole()));
			
		}
       // inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
         //       "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
		
		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
				.filter(user -> user.getUsername().equals(username)).findFirst();

		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return findFirst.get();
	}
}
