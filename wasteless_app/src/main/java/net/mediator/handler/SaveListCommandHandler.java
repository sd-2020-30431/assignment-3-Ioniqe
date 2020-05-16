package net.mediator.handler;

import net.mediator.interfaces.Handler;
import net.mediator.command.SaveListCommand;
import net.mediator.response.SaveListCommandResponse;
import net.service.command.ListServiceCommand;
import org.springframework.stereotype.Component;

@Component
public class SaveListCommandHandler implements Handler<SaveListCommand, SaveListCommandResponse> {

    private final ListServiceCommand listServiceCommand;

    public SaveListCommandHandler(ListServiceCommand listServiceCommand) {
        this.listServiceCommand = listServiceCommand;
    }

    @Override
    public SaveListCommandResponse handle(SaveListCommand request) {
        return new SaveListCommandResponse(listServiceCommand.save(request.getList()));
    }
}
