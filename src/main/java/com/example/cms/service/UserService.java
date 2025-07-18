package com.example.cms.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.cms.entity.User;
import com.example.cms.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {


	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	
	
	
	public void registerUser(User user) {
		
		Optional<User> user1=userRepository.findByEmail(user.getEmail());
		
		if(user1.isPresent()) {
			
			throw new IllegalStateException("Email '"+user.getEmail() +"'is already in use.");
			
		}
		
		String encodedPassword=passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		user.setRole("ROLE_USER");
		
		userRepository.save(user);
		
	}




	 @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        return userRepository.findByEmail(email)
	                .orElseThrow(() ->
	                        new UsernameNotFoundException("User not found with email: " + email)
	                );
	    }
	
	
	
	
}
