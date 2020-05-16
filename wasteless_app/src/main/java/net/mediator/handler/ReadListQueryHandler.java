package net.mediator.handler;

import net.mediator.interfaces.Handler;
import net.mediator.query.ListQuery;
import net.mediator.response.FindListQueryResponse;
import net.service.query.ListServiceQuery;
import org.springframework.stereotype.Component;

@Component
public class ReadListQueryHandler implements Handler<ListQuery, FindListQueryResponse> {

    private final ListServiceQuery listServiceQuery;

    public ReadListQueryHandler(ListServiceQuery listServiceQuery) {
        this.listServiceQuery = listServiceQuery;
    }

    @Override
    public FindListQueryResponse handle(ListQuery request) {
        return new FindListQueryResponse(listServiceQuery.findListById(request.getListId()));
    }
}
