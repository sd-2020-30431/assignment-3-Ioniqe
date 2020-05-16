package net.mediator.handler;

import net.mediator.interfaces.Handler;
import net.mediator.query.FindUserQuery;
import net.mediator.response.FindUserResponse;
import net.service.query.UserServiceQuery;
import org.springframework.stereotype.Component;

@Component
public class FindUserQueryHandler implements Handler<FindUserQuery, FindUserResponse> {
    private final UserServiceQuery userServiceQuery;

    public FindUserQueryHandler(UserServiceQuery userServiceQuery) {
        this.userServiceQuery = userServiceQuery;
    }

    @Override
    public FindUserResponse handle(FindUserQuery request) {
        return new FindUserResponse(userServiceQuery.findUserByUsername(request.getUsername()));
    }
}
