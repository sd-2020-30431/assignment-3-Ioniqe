package net.mediator.handler;

import net.mediator.interfaces.Handler;
import net.mediator.query.ListQuery;
import net.mediator.response.FindListsQueryResponse;
import net.service.query.ListServiceQuery;
import org.springframework.stereotype.Component;

@Component
public class ReadListsQueryHandler implements Handler<ListQuery, FindListsQueryResponse> {

    private final ListServiceQuery listServiceQuery;

    public ReadListsQueryHandler(ListServiceQuery listServiceQuery) {
        this.listServiceQuery = listServiceQuery;
    }

    @Override
    public FindListsQueryResponse handle(ListQuery request) {
        return new FindListsQueryResponse(listServiceQuery.findAllListsByUsername(request.getUsername()));
    }
}
