package net.mediator.handler;

import net.mediator.command.DeleteListCommand;
import net.mediator.interfaces.Handler;
import net.mediator.response.GenericResponse;
import net.service.command.ListServiceCommand;
import org.springframework.stereotype.Component;

@Component
public class DeleteListCommandHandler implements Handler<DeleteListCommand, GenericResponse> {

    private final ListServiceCommand listServiceCommand;

    public DeleteListCommandHandler(ListServiceCommand listServiceCommand) {
        this.listServiceCommand = listServiceCommand;
    }

    @Override
    public GenericResponse handle(DeleteListCommand request) {
        listServiceCommand.delete(request.getId());
        return new GenericResponse();
    }
}
