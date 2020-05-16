package net.mediator.response;

import net.mediator.interfaces.Response;

public class FindGoalQueryResponse implements Response {
    private int calories;

    public FindGoalQueryResponse(int calories) {
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
