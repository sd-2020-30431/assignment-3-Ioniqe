package net.mediator.handler;

import net.mediator.interfaces.Handler;
import net.mediator.query.GoalQuery;
import net.mediator.response.FindGoalQueryResponse;
import net.service.query.GoalServiceQuery;
import org.springframework.stereotype.Component;

@Component
public class ReadGoalQueryHandler implements Handler<GoalQuery, FindGoalQueryResponse> {

    private final GoalServiceQuery goalServiceQuery;

    public ReadGoalQueryHandler(GoalServiceQuery goalServiceQuery) {
        this.goalServiceQuery = goalServiceQuery;
    }

    @Override
    public FindGoalQueryResponse handle(GoalQuery request) {
        return new FindGoalQueryResponse(goalServiceQuery.calculateAmountOfDailyCalories(request.getUser().getUsername()));
    }
}
