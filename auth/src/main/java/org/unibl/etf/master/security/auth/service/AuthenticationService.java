package org.unibl.etf.master.security.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.master.security.auth.configuration.security.AuthUserDetails;
import org.unibl.etf.master.security.auth.exception.UnauthorizedException;
import org.unibl.etf.master.security.auth.model.mapper.UserMapper;
import org.unibl.etf.master.security.auth.model.response.UserDetailsResponse;
import org.unibl.etf.master.security.auth.repository.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public AuthenticationService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDetailsResponse getUserDetails(Object principal) throws UnauthorizedException {
        if(principal instanceof AuthUserDetails)
            return userMapper.userToUserDetailsResponse(((AuthUserDetails)principal).getUser());

        return userRepository.findUserByUsername(principal.toString())
                .map(userMapper::userToUserDetailsResponse).orElseThrow(UnauthorizedException::new);
    }
}