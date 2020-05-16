package net.mediator.handler;

import net.mediator.interfaces.Handler;
import net.mediator.query.CharityQuery;
import net.mediator.response.FindCharitiesQueryResponse;
import net.service.query.CharityServiceQuery;
import org.springframework.stereotype.Component;

@Component
public class ReadCharitiesQueryHandler implements Handler<CharityQuery, FindCharitiesQueryResponse> {

    private final CharityServiceQuery charityServiceQuery;

    public ReadCharitiesQueryHandler(CharityServiceQuery charityServiceQuery) {
        this.charityServiceQuery = charityServiceQuery;
    }

    @Override
    public FindCharitiesQueryResponse handle(CharityQuery request) {
        return new FindCharitiesQueryResponse(charityServiceQuery.listAll());
    }
}
