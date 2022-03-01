package com.example.demo.security;

import java.util.Optional;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        Optional<User> user = userRepository.findOneByName(username);
         
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new AuthUserDetails(user.get());
    }
 
}