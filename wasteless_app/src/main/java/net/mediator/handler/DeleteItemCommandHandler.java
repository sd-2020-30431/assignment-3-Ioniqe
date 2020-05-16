package net.mediator.handler;

import net.mediator.command.DeleteItemCommand;
import net.mediator.interfaces.Handler;
import net.mediator.response.GenericResponse;
import net.service.command.ItemServiceCommand;
import org.springframework.stereotype.Component;

@Component
public class DeleteItemCommandHandler implements Handler<DeleteItemCommand, GenericResponse> {

    private final ItemServiceCommand itemServiceCommand;

    public DeleteItemCommandHandler(ItemServiceCommand itemServiceCommand) {
        this.itemServiceCommand = itemServiceCommand;
    }

    @Override
    public GenericResponse handle(DeleteItemCommand request) {
        itemServiceCommand.delete(request.getId());
        return new GenericResponse();
    }
}
