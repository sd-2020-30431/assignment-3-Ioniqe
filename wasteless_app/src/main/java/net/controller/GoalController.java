package net.controller;

import net.mediator.Mediator;
import net.mediator.handler.FindUserQueryHandler;
import net.mediator.handler.ReadGoalQueryHandler;
import net.mediator.query.FindUserQuery;
import net.mediator.query.GoalQuery;
import net.mediator.response.FindGoalQueryResponse;
import net.mediator.response.FindUserResponse;
import net.model.User;
import net.model.dto.ListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class GoalController {

    private final Mediator mediator;

    public GoalController(Mediator mediator) {
        this.mediator = mediator;
    }


    @RequestMapping(value = "/lists/displayGoalStats/{username}", method = RequestMethod.GET)
    public ResponseEntity<ListDTO> createGoal(@PathVariable(value = "username") String username) {

        FindUserQuery request = new FindUserQuery(username);
        FindUserQueryHandler handler = (FindUserQueryHandler)mediator.<FindUserQuery, FindUserResponse>handle(request);
        FindUserResponse response = handler.handle(request);
        User user = response.getUser();

        String waste;
        int dailyCalories;

        GoalQuery requestGoal = new GoalQuery(user);
        ReadGoalQueryHandler handlerGoal = (ReadGoalQueryHandler)mediator.<GoalQuery, FindGoalQueryResponse>handle(requestGoal);
        FindGoalQueryResponse responseGoal = handlerGoal.handle(requestGoal);
        dailyCalories = responseGoal.getCalories();

        if (dailyCalories > user.getGoal()) {
            waste = " a waste of " + (dailyCalories - user.getGoal()) + " calories. In order to make no waste, you would need to consume " +
                    dailyCalories + " calories per day, or donate to a charity";
        } else {
            waste = "no waste.";
        }

        ListDTO l = new ListDTO(waste);
        return new ResponseEntity<ListDTO>(l, HttpStatus.OK);

    }



}
