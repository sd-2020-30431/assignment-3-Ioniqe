package net.controller;

import net.mediator.Mediator;
import net.mediator.command.SaveUserCommand;
import net.mediator.handler.FindUserQueryHandler;
import net.mediator.handler.SaveUserCommandHandler;
import net.mediator.query.FindUserQuery;
import net.mediator.response.FindUserResponse;
import net.mediator.response.SaveUserCommandResponse;
import net.model.User;
import net.model.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class UserController {

    private final Mediator mediator;
    public UserController(Mediator mediator) {
        this.mediator = mediator;
    }

    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    public ResponseEntity<User> update(@RequestBody User myUser) {
        String username = myUser.getUsername().trim();
        String password = myUser.getPassword().trim();

        FindUserQuery request = new FindUserQuery(myUser.getUsername(), myUser.getPassword());
        FindUserQueryHandler handler = (FindUserQueryHandler)mediator.<FindUserQuery, FindUserResponse>handle(request);
        FindUserResponse response = handler.handle(request);
        User verifyUser = response.getUser();

        if (verifyUser == null) {
            return new ResponseEntity("Invalid User", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(new UserDTO(username, password), HttpStatus.OK);
    }

    @RequestMapping(value = "/lists/updateUserGoal", method = RequestMethod.PUT)
    public ResponseEntity editUser(@RequestBody User updatedUserGoal) {
        FindUserQuery request = new FindUserQuery(updatedUserGoal.getUsername(), updatedUserGoal.getPassword());
        FindUserQueryHandler handler = (FindUserQueryHandler)mediator.<FindUserQuery, FindUserResponse>handle(request);
        FindUserResponse response = handler.handle(request);
        response.getUser().setGoal(updatedUserGoal.getGoal());

        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public ResponseEntity saveUser(@RequestBody User newUser) {

        SaveUserCommand request = new SaveUserCommand(newUser);
        SaveUserCommandHandler handler = (SaveUserCommandHandler)mediator.<SaveUserCommand, SaveUserCommandResponse>handle(request);
        SaveUserCommandResponse response = handler.handle(request);
        response.setUser(newUser);
        return new ResponseEntity(HttpStatus.OK);
    }


}
