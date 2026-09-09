package com.project.wonderboot.userdetails;

import com.project.wonderboot.repository.WonderUserRepository;
import com.project.wonderboot.models.user.WonderUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final WonderUserRepository wonderUserRepository;

    public CustomUserDetailsService(WonderUserRepository wonderUserRepository) {
        this.wonderUserRepository = wonderUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WonderUser wonderUser = wonderUserRepository
                .findByProfileUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuario no encontrado: " + username
                        )
                );
        return new User(username, wonderUser.getPassword(), List.of());
    }
}
