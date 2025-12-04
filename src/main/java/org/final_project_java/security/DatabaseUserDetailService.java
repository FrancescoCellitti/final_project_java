package org.final_project_java.security;

import java.util.Optional;

import org.final_project_java.model.User;
import org.final_project_java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailService implements UserDetailsService{
    
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> userAttempt =  repository.findByUsername(username);


        if (userAttempt.isEmpty()) {
            throw new UsernameNotFoundException("unimplementd method 'loadUserByUsername'");

        }

        return new DatabaseUserDetails(userAttempt.get());
    }
}
