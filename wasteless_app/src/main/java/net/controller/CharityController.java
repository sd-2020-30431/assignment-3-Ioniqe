package net.controller;

import net.mediator.Mediator;
import net.mediator.handler.ReadCharitiesQueryHandler;
import net.mediator.query.CharityQuery;
import net.mediator.response.FindCharitiesQueryResponse;
import net.model.Charity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class CharityController {
    private final Mediator mediator;

    public CharityController(Mediator mediator) {
        this.mediator = mediator;
    }

    @RequestMapping(value = "/donateToCharity/{itemId}", method = RequestMethod.GET)
    public ResponseEntity<List<Charity>> viewCharities(@PathVariable(name = "itemId") int itemId) {

        CharityQuery request = new CharityQuery();
        ReadCharitiesQueryHandler handler = (ReadCharitiesQueryHandler)mediator.<CharityQuery, FindCharitiesQueryResponse> handle(request);
        FindCharitiesQueryResponse response = handler.handle(request);
        return new ResponseEntity<>(response.getCharities(), HttpStatus.OK);

    }
}
