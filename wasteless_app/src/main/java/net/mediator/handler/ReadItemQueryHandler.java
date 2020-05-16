package net.mediator.handler;

import net.mediator.interfaces.Handler;
import net.mediator.query.ItemQuery;
import net.mediator.response.FindItemQueryResponse;
import net.service.query.ItemServiceQuery;
import org.springframework.stereotype.Component;

@Component
public class ReadItemQueryHandler implements Handler<ItemQuery, FindItemQueryResponse> {

    private final ItemServiceQuery itemServiceQuery;

    public ReadItemQueryHandler(ItemServiceQuery itemServiceQuery) {
        this.itemServiceQuery = itemServiceQuery;
    }


    @Override
    public FindItemQueryResponse handle(ItemQuery request) {
        return new FindItemQueryResponse(itemServiceQuery.getItemById(request.getItemId()));
    }
}
