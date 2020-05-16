package net.mediator.response;

import net.mediator.interfaces.Response;
import net.model.User;

public class SaveUserCommandResponse implements Response {
    private User user;

    public SaveUserCommandResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
