package net.controller;

import net.model.Lists;
import net.model.User;
import net.model.dto.ListDTO;
//import net.service.ListService;
//import net.service.UserService;
import net.service.command.ListServiceCommand;
import net.service.query.ListServiceQuery;
import net.service.query.UserServiceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
//@Controller
public class ListsController {
//    @Autowired
//    private ListService listService;
//
//    @Autowired
//    private UserService userService;

    private User user;

    @Autowired
    ListServiceQuery listServiceQuery = new ListServiceQuery();
    @Autowired
    ListServiceCommand listServiceCommand = new ListServiceCommand();
    @Autowired
    UserServiceQuery userServiceQuery = new UserServiceQuery();

    @RequestMapping(value = "/lists/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<Lists>> listLists(@PathVariable(name = "username") String username) { //@RequestBody User user
        List<Lists> listOfLists = listServiceQuery.findAllListsByUsername(username);
        return new ResponseEntity<>(listOfLists, HttpStatus.OK);
    }

    @RequestMapping(value = "/lists/{username}", method = RequestMethod.POST)
    public ResponseEntity newList(@PathVariable(name = "username") String username, @RequestBody Lists list) {
        User user = userServiceQuery.findUserByUsername(username);
        list.setUser(user);
        listServiceCommand.save(list);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{username}/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable(name = "id") int id, @PathVariable(name = "username") String username) {
        listServiceCommand.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
