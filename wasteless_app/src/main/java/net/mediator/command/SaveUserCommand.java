package net.mediator.command;

import net.mediator.interfaces.Request;
import net.model.User;

public class SaveUserCommand implements Request {
    private User user;

    public SaveUserCommand(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
