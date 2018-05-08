package com.starcast.account.service;

import com.starcast.account.model.User;
import com.starcast.account.repository.RoleRepository;
import com.starcast.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import static com.starcast.account.validator.FieldValidator.isValidEmail;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        user.setCountrycode("91");
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
    	if(isValidEmail(username)) {
    		return userRepository.findByEmail(username);
    	}
        return userRepository.findByMobileNumber(Long.valueOf(username));
    }
}
