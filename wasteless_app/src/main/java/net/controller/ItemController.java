package net.controller;

import net.model.Item;
import net.model.Lists;
import net.model.dto.ItemDTO;
//import net.service.ItemService;
//import net.service.ListService;
import net.service.command.ItemServiceCommand;
import net.service.query.ItemServiceQuery;
import net.service.query.ListServiceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

//@Controller

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class ItemController {

//    @Autowired
//    private ItemService itemService;
//
//    @Autowired
//    private ListService listService;

    //private Lists list;

    @Autowired
    ItemServiceQuery itemServiceQuery = new ItemServiceQuery(); //----------------------
    @Autowired
    ListServiceQuery listServiceQuery = new ListServiceQuery();
    @Autowired
    ItemServiceCommand itemServiceCommand = new ItemServiceCommand();


    @RequestMapping(value = "/editList/{username}/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> viewHomePage(@PathVariable(name = "id") long id, @PathVariable(name = "username") String username) {



        List<Item> listOfItems = itemServiceQuery.findItems(id);
//        this.list = list;
        return new ResponseEntity<>(listOfItems, HttpStatus.OK);
    }

    @RequestMapping(value = "/newItem/{username}/{listId}", method = RequestMethod.POST)
    public ResponseEntity saveItem(@RequestBody Item item, @PathVariable(name = "listId") long id, @PathVariable(name = "username") String username) {


        Lists list = listServiceQuery.findListById(id);
        item.setList(list);
        itemServiceCommand.save(item);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/editList/editItem/{username}/{listId}/{itemId}", method = RequestMethod.POST)
    public ResponseEntity editItem(@RequestBody Item item, @PathVariable(name = "username") String username, @PathVariable(name = "listId") long listId, @PathVariable(name = "itemId") long itemId) {
        Lists list = listServiceQuery.findListById(listId);
        item.setList(list);
        itemServiceCommand.save(item);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/getItem/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<Item> getItem( @PathVariable(name = "itemId") long itemId) {
        Item item = itemServiceQuery.getItemById(itemId);

        return new ResponseEntity(new ItemDTO(item.getId(), item.getName(), item.getQuantity(), item.getCalories(), item.getPurchaseDate(), item.getExpirationDate(), item.getPurchaseDate()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/donate_item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteItem(@PathVariable(name = "id") int id) {
        itemServiceCommand.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
