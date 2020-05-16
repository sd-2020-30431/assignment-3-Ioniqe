package net.controller;

import net.model.Item;
import net.model.Lists;
import net.model.User;
import net.model.dto.UserDTO;
import net.observer.ItemExpirationObserver;
//import net.service.ItemService;
//import net.service.ListService;
//import net.service.UserService;
import net.service.command.UserServiceCommand;
import net.service.query.UserServiceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
//@Controller
public class UserController {

//    @Autowired
//    private UserService service;

    private User user;

    @Autowired
    UserServiceQuery userServiceQuery = new UserServiceQuery();
    @Autowired
    UserServiceCommand userServiceCommand = new UserServiceCommand();

    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    public ResponseEntity<User> login2() {
        User user = new User((long) 1123, "test", "test");
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    public ResponseEntity<User> update(@RequestBody User myUser) {
        String username = myUser.getUsername().trim();
        String password = myUser.getPassword().trim();
        User verifyUser = userServiceQuery.findUser(username, password);
        if (verifyUser == null) {
            return new ResponseEntity("Invalid User", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(new UserDTO(username, password), HttpStatus.OK);
    }

    @RequestMapping(value = "/lists/updateUserGoal", method = RequestMethod.PUT)
    public ResponseEntity editUser(@RequestBody User updatedUserGoal) {
        User updatedUser = userServiceQuery.findUserByUsername(updatedUserGoal.getUsername());
        updatedUser.setGoal(updatedUserGoal.getGoal());
        userServiceCommand.save(updatedUser);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public ResponseEntity saveUser(@RequestBody User newUser) {
        userServiceCommand.save(newUser);
        return new ResponseEntity(HttpStatus.OK);
    }


}
