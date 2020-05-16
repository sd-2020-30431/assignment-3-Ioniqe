package net.mediator.query;

import net.mediator.interfaces.Request;
import net.model.User;

public class GoalQuery implements Request{
    private User user;

    public GoalQuery(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
