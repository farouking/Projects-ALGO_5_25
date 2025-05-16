package com.codingdojo.choretracker.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.choretracker.models.LoginUser;
import com.codingdojo.choretracker.models.User;
import com.codingdojo.choretracker.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // Register a new user
    public User register(User newUser, BindingResult result) {
        // Check if email already exists
        Optional<User> potentialUser = userRepository.findByEmail(newUser.getEmail());
        if(potentialUser.isPresent()) {
            result.rejectValue("email", "Matches", "Email already exists!");
        }
        
        // Check if passwords match
        if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "Matches", "Passwords must match!");
        }
        
        // If there are errors, return null
        if(result.hasErrors()) {
            return null;
        }
        
        // Hash password
        String hashedPwd = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashedPwd);
        
        // Save user to database
        return userRepository.save(newUser);
    }
    
    // Login
    public User login(LoginUser loginUser, BindingResult result) {
        // Find user by email
        Optional<User> potentialUser = userRepository.findByEmail(loginUser.getEmail());
        
        // Check if user exists
        if(!potentialUser.isPresent()) {
            result.rejectValue("email", "Matches", "Unknown email!");
            return null;
        }
        
        // Get user from database
        User user = potentialUser.get();
        
        // Check if password matches
        if(!BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid password!");
            return null;
        }
        
        // Return user
        return user;
    }
    
    // Find user by id
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }
}