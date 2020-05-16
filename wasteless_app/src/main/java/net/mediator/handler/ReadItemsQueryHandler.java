package net.mediator.handler;

import net.mediator.interfaces.Handler;
import net.mediator.query.ItemsQuery;
import net.mediator.response.FindItemsQueryResponse;
import net.service.query.ItemServiceQuery;
import org.springframework.stereotype.Component;

@Component
public class ReadItemsQueryHandler implements Handler<ItemsQuery, FindItemsQueryResponse> {

    private final ItemServiceQuery itemServiceQuery;

    public ReadItemsQueryHandler(ItemServiceQuery itemServiceQuery) {
        this.itemServiceQuery = itemServiceQuery;
    }

    @Override
    public FindItemsQueryResponse handle(ItemsQuery request) {
        return new FindItemsQueryResponse(itemServiceQuery.findItems(request.getListId()));
    }
}
