package net.controller;

import net.mediator.Mediator;
import net.mediator.handler.ReadItemsQueryHandler;
import net.mediator.handler.ReadListsQueryHandler;
import net.mediator.query.ItemsQuery;
import net.mediator.query.ListQuery;
import net.mediator.response.FindItemsQueryResponse;
import net.mediator.response.FindListsQueryResponse;
import net.model.Item;
import net.model.Lists;
import net.observer.ItemExpirationObserver;
//import net.service.ItemService;
//import net.service.ListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class NotificationController {

//    @Autowired
//    ListServiceQuery listServiceQuery = new ListServiceQuery();
//    @Autowired
//    ItemServiceQuery itemServiceQuery = new ItemServiceQuery();

    private final Mediator mediator;

    public NotificationController(Mediator mediator) {
        this.mediator = mediator;
    }

    @RequestMapping(value = "/notification/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getExpiredLists(@PathVariable(name = "username") String username) {

//        List<Lists> listsOfUser = listServiceQuery.findAllListsByUsername(username);
////        List<Item> itemsOfUser = new ArrayList<>();
////        for (Lists list : listsOfUser) {
////            itemsOfUser.addAll(itemServiceQuery.findItems(list.getId()));
////        }
////
////        List<Item> expiredItems = new ArrayList<>();
////        new ItemExpirationObserver(itemsOfUser, (list) -> {
////            expiredItems.clear();
////            expiredItems.addAll(list);
////        });
////
////
////        return new ResponseEntity(expiredItems, HttpStatus.OK);

        ListQuery request = new ListQuery(username);
        ReadListsQueryHandler handler = (ReadListsQueryHandler) mediator.<ListQuery, FindListsQueryResponse>handle(request);
        FindListsQueryResponse response = handler.handle(request);
        List<Lists> listsOfUser = response.getLists();

        List<Item> itemsOfUser = new ArrayList<>();


        for (Lists list : listsOfUser) {
            ItemsQuery requestItems = new ItemsQuery(list.getId());
            ReadItemsQueryHandler handlerItems = (ReadItemsQueryHandler) mediator.<ItemsQuery, FindItemsQueryResponse>handle(requestItems);
            FindItemsQueryResponse responseItems = handlerItems.handle(requestItems);
            itemsOfUser.addAll(responseItems.getItems());
        }

        List<Item> expiredItems = new ArrayList<>();
        new ItemExpirationObserver(itemsOfUser, (list) -> {
            expiredItems.clear();
            expiredItems.addAll(list);
        });

        return new ResponseEntity(expiredItems, HttpStatus.OK);
    }
}
