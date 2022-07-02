package com.akane.j2eetd.controllers;

import com.akane.j2eetd.entities.User;
import com.akane.j2eetd.exceptions.ResourceNotFoundException;
import com.akane.j2eetd.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public User get(@PathVariable(name = "username") String username) throws ResourceNotFoundException {
        return userService.getUserById(username);
    }

//    @RequestMapping(path = "?firstName={firstName}", method = RequestMethod.GET)
//    public User getByFirstName(@PathVariable(name = "firstName") String firstName) throws ResourceNotFoundException {
//        return userService.getUserByUsername(firstName);
//    }

    @RequestMapping(method = RequestMethod.PUT)
    public User update(@RequestBody @Valid User user) {
        return userService.update(user);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody @Valid User user) {
        return userService.create(user);
    }

    @Operation(summary = "Récupération de tous les utilisateurs")
    @RequestMapping(path = "/_all", method = RequestMethod.GET)
    public List<User> getAllUsers() { return userService.getAllUsers();
    }

    @Operation(summary = "Suppression d'un utilisateur à partir de son identifiant")
    @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(value = "username") String username) {
        userService.deleteUser(username);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<User> getUsers(Pageable pageable) {
        return userService.getUsersWithPaging(pageable);
    }

    @Operation(summary = "Mise à jour du mot de passe d'un utilisateur")
    @RequestMapping(path = "/update-password", method = RequestMethod.GET)
    public void setPassword(@RequestParam (value = "username") String userName,
                            @RequestParam(value = "password") String newPassword) throws IllegalAccessException {
        userService.setPassword(userName, newPassword);
    }

}
