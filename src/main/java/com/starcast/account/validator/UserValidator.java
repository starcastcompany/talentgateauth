package com.starcast.account.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.starcast.account.model.User;
import com.starcast.account.service.UserService;

@Component
public class UserValidator implements Validator {
	
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    	    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobilenumber", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "NotEmpty");
        
        if(null!= user.getMobilenumber())
        {
        	if(String.valueOf(user.getMobilenumber()).length() <10)
        		errors.rejectValue("username", "Size.userForm.mobilenumber");
        	
        	if (userService.findByUsername(String.valueOf(user.getMobilenumber())) != null) {
                errors.rejectValue("mobilenumber", "Duplicate.userForm.mobilenumber");
            }
        }
        
        if(null!= user.getEmail())
        {
        	if (!validate(user.getEmail())) {
                errors.rejectValue("username", "Size.userForm.email");
            }
        	
        	if (userService.findByUsername(String.valueOf(user.getEmail())) != null) {
                errors.rejectValue("email", "Duplicate.userForm.email");
            }
        }

        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
    
    	public static boolean validate(String emailStr) {
    	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
    	        return matcher.find();
    	}
}
