package net.controller;

import net.mediator.Mediator;
import net.mediator.command.DeleteItemCommand;
import net.mediator.command.SaveItemCommand;
import net.mediator.handler.*;
import net.mediator.query.ItemQuery;
import net.mediator.query.ItemsQuery;
import net.mediator.query.ListQuery;
import net.mediator.response.*;
import net.model.Item;
import net.model.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class ItemController {

    private final Mediator mediator;

    public ItemController(Mediator mediator) {
        this.mediator = mediator;
    }

    private Lists getListOfItem(String username, long id) {
        ListQuery requestList = new ListQuery(username);
        ReadListsQueryHandler handlerList = (ReadListsQueryHandler) mediator.<ListQuery, FindListsQueryResponse>handle(requestList);
        FindListsQueryResponse responseList = handlerList.handle(requestList);
        List<Lists> listsOfUser = responseList.getLists();

        Lists list = new Lists();
        for(Lists l : listsOfUser){
            if(l.getId() == id)
            {
                list = l;
                break;
            }
        }
        return list;
    }

    @RequestMapping(value = "/editList/{username}/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> viewHomePage(@PathVariable(name = "id") long id, @PathVariable(name = "username") String username) {

        ItemsQuery request = new ItemsQuery(id);
        ReadItemsQueryHandler handler = (ReadItemsQueryHandler) mediator.<ItemsQuery, FindItemsQueryResponse>handle(request);
        FindItemsQueryResponse response = handler.handle(request);
        return new ResponseEntity<>(response.getItems(), HttpStatus.OK);
    }

    @RequestMapping(value = "/newItem/{username}/{listId}", method = RequestMethod.POST)
    public ResponseEntity saveItem(@RequestBody Item item, @PathVariable(name = "listId") long id, @PathVariable(name = "username") String username) {

        Lists list = getListOfItem(username, id);
        item.setList(list);

        SaveItemCommand request = new SaveItemCommand(item);
        SaveItemCommandHandler handler = (SaveItemCommandHandler) mediator.<SaveItemCommand, SaveItemCommandResponse>handle(request);
        SaveItemCommandResponse response = handler.handle(request);
        response.setItem(item);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/editList/editItem/{username}/{listId}/{itemId}", method = RequestMethod.POST)
    public ResponseEntity editItem(@RequestBody Item item, @PathVariable(name = "username") String username, @PathVariable(name = "listId") long listId, @PathVariable(name = "itemId") long itemId) {

        Lists list = getListOfItem(username, listId);
        item.setList(list);

        SaveItemCommand request = new SaveItemCommand(item);
        SaveItemCommandHandler handler = (SaveItemCommandHandler) mediator.<SaveItemCommand, SaveItemCommandResponse>handle(request);
        SaveItemCommandResponse response = handler.handle(request);
        response.setItem(item);
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/getItem/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<Item> getItem(@PathVariable(name = "itemId") long itemId) {

        ItemQuery request = new ItemQuery(itemId);
        ReadItemQueryHandler handler = (ReadItemQueryHandler) mediator.<ItemQuery, FindItemQueryResponse>handle(request);
        FindItemQueryResponse response = handler.handle(request);
        return new ResponseEntity(response.getItem(), HttpStatus.OK);
    }

    @RequestMapping(value = "/donate_item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteItem(@PathVariable(name = "id") int id) {

        DeleteItemCommand request = new DeleteItemCommand(id);
        DeleteItemCommandHandler handler = (DeleteItemCommandHandler) mediator.<DeleteItemCommand, GenericResponse>handle(request);
        handler.handle(request);
        return new ResponseEntity(HttpStatus.OK);
    }

}
