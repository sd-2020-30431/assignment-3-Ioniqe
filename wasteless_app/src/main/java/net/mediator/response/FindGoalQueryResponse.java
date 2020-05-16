package net.mediator.response;

import net.mediator.interfaces.Response;

public class FindGoalQueryResponse implements Response {
    private String username;
    private long userId;

    private int calories;

    public FindGoalQueryResponse(String username, long userId, int calories) {
        this.username = username;
        this.userId = userId;
        this.calories = calories;
    }

    public FindGoalQueryResponse(long userId) {
        this.userId = userId;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
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
