package net.mediator.handler;

import net.mediator.command.SaveUserCommand;
import net.mediator.interfaces.Handler;
import net.mediator.response.SaveUserCommandResponse;
import net.service.command.UserServiceCommand;
import org.springframework.stereotype.Component;

@Component
public class SaveUserCommandHandler implements Handler<SaveUserCommand, SaveUserCommandResponse> {

    private final UserServiceCommand userServiceCommand;

    public SaveUserCommandHandler(UserServiceCommand userServiceCommand) {
        this.userServiceCommand = userServiceCommand;
    }

    @Override
    public SaveUserCommandResponse handle(SaveUserCommand request) {
        return new SaveUserCommandResponse(userServiceCommand.save(request.getUser()));
    }
}
