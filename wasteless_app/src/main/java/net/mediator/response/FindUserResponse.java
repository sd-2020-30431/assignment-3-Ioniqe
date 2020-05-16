package net.mediator.response;

import net.mediator.interfaces.Response;
import net.model.User;

public class FindUserResponse implements Response {
    private User user;

    public FindUserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
