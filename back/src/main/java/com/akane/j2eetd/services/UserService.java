package com.akane.j2eetd.services;

import com.akane.j2eetd.entities.User;
import com.akane.j2eetd.exceptions.ResourceNotFormatException;
import com.akane.j2eetd.exceptions.ResourceNotFoundException;
import com.akane.j2eetd.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    public User update(User user) {
        if (StringUtils.isNotEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        logger.info("Update user... {}", user.getUsername());
        return userRepository.save(user);
    }

    @ApiResponse(responseCode = "400", description = "Utilisateur pas au bon format")
    public User create(User user) throws ResourceNotFormatException {
        try{
            if (StringUtils.isNotEmpty(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            logger.info("Saving user... {}", user.getUsername());
            return userRepository.save(user);

        }catch(Exception e){
            throw new ResourceNotFormatException(User.class);
        }


    }

    @Operation(summary = "R??cup??ration d'un utilisateur ?? partir de son identifiant")
    @ApiResponse(responseCode = "404", description = "Utilisateur non trouv??")
    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    public User getUserById(String username) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            logger.info("Get user... {}", user.get().getUsername());
            return user.get();
        }
        throw new ResourceNotFoundException(User.class, username);
    }

    @RolesAllowed({ "ROLE_ADMIN" })
    public List<User> getAllUsers() {
        logger.info("Get all user ");
        return userRepository.findAll();
    }

    @RolesAllowed("ROLE_ADMIN")
    @ApiResponse(responseCode = "404", description = "Utilisateur existe pas")
    public void deleteUser(String username) throws ResourceNotFoundException {
        try{
            userRepository.deleteById(username);
            logger.info("Deleted user... {}", username);
        }catch(Exception e){
            throw new ResourceNotFoundException(User.class, username);
        }
    }

    @RolesAllowed({ "ROLE_ADMIN"})
    public Page<User> getUsersWithPaging(Pageable pageable) {
        logger.info("Get all user with paging");
        return userRepository.findAll(pageable);
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.get().getRole()));
            logger.info("Load user... {}", username);
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
                    authorities);
        }
        throw new UsernameNotFoundException("User '" + username + "' not found or inactive");
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
    public void setPassword(String userName, String newPassword) {
        Optional<User> user = userRepository.findById(userName);
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        if (user.isPresent()) {
            user.get().setPassword(encodedNewPassword);
            logger.info("Changed password user... {}", userName);
            userRepository.save(user.get());
        }
    }
}
