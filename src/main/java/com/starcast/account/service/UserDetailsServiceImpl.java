package com.starcast.account.service;

import static com.starcast.account.validator.FieldValidator.isValidEmail;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.starcast.account.model.Role;
import com.starcast.account.model.User;
import com.starcast.account.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	String userName = null;
    	User user = null;
    	if(null!= username) {
    		if(isValidEmail(username)) {
        		user = userRepository.findByEmail(username);
        		userName = user.getEmail();
        	}else {
        		user = userRepository.findByMobileNumber(Long.valueOf(username));
        		userName = String.valueOf(user.getMobilenumber());
        	}
        }
    	Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
              
        return new org.springframework.security.core.userdetails.User(userName, user.getPassword(), grantedAuthorities);
    }
	
	
}
