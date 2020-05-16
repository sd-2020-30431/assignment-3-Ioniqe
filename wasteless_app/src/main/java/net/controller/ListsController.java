package net.controller;

import net.mediator.Mediator;
import net.mediator.command.DeleteListCommand;
import net.mediator.command.SaveListCommand;
import net.mediator.handler.DeleteListCommandHandler;
import net.mediator.handler.FindUserQueryHandler;
import net.mediator.handler.ReadListsQueryHandler;
import net.mediator.handler.SaveListCommandHandler;
import net.mediator.query.FindUserQuery;
import net.mediator.query.ListQuery;
import net.mediator.response.FindUserResponse;
import net.mediator.response.GenericResponse;
import net.mediator.response.FindListsQueryResponse;
import net.mediator.response.SaveListCommandResponse;
import net.model.Lists;
import net.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class ListsController {

    private User user;

    private final Mediator mediator;

    public ListsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @RequestMapping(value = "/lists/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<Lists>> listLists(@PathVariable(name = "username") String username) {
//        List<Lists> listOfLists = listServiceQuery.findAllListsByUsername(username);
//        return new ResponseEntity<>(listOfLists, HttpStatus.OK);

        ListQuery request = new ListQuery(username);
        ReadListsQueryHandler handler = (ReadListsQueryHandler) mediator.<ListQuery, FindListsQueryResponse>handle(request);
        FindListsQueryResponse response = handler.handle(request);
        return new ResponseEntity<>(response.getLists(), HttpStatus.OK);
    }

    @RequestMapping(value = "/lists/{username}", method = RequestMethod.POST)
    public ResponseEntity newList(@PathVariable(name = "username") String username, @RequestBody Lists list) {
//        User user = userServiceQuery.findUserByUsername(username);
//        list.setUser(user);
//        listServiceCommand.save(list);
//        return new ResponseEntity(HttpStatus.OK);

        FindUserQuery requestUser = new FindUserQuery(username);
        FindUserQueryHandler handlerUser = (FindUserQueryHandler) mediator.<FindUserQuery, FindUserResponse>handle(requestUser);
        FindUserResponse responseUser = handlerUser.handle(requestUser);
        User user = responseUser.getUser();

        list.setUser(user);

        SaveListCommand request = new SaveListCommand(list);
        SaveListCommandHandler handler = (SaveListCommandHandler) mediator.<SaveListCommand, SaveListCommandResponse>handle(request);
        SaveListCommandResponse response = handler.handle(request);


        response.setList(list);

//        return new ResponseEntity<>(response.getList(), HttpStatus.OK);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{username}/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteList(@PathVariable(name = "id") int id, @PathVariable(name = "username") String username) {
//        listServiceCommand.delete(id);
//        return new ResponseEntity(HttpStatus.OK);
        DeleteListCommand request = new DeleteListCommand(id);
        DeleteListCommandHandler handler = (DeleteListCommandHandler) mediator.<DeleteListCommand, GenericResponse>handle(request);
        handler.handle(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
