package net.mediator.query;

import net.mediator.interfaces.Request;

public class GoalQuery implements Request{
    private String username;
    private long userId;

    public GoalQuery(String username, long userId) {
        this.username = username;
        this.userId = userId;
    }

    public GoalQuery(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
