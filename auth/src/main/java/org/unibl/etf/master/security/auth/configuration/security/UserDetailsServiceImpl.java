package org.unibl.etf.master.security.auth.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.unibl.etf.master.security.auth.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl() {}
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .map(AuthUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
    }
 
}