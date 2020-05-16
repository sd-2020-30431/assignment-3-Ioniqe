package net.mediator.handler;

import net.mediator.command.SaveItemCommand;
import net.mediator.interfaces.Handler;
import net.mediator.response.SaveItemCommandResponse;
import net.service.command.ItemServiceCommand;
import org.springframework.stereotype.Component;

@Component
public class SaveItemCommandHandler implements Handler<SaveItemCommand, SaveItemCommandResponse> {

    private final ItemServiceCommand itemServiceCommand;

    public SaveItemCommandHandler(ItemServiceCommand itemServiceCommand) {
        this.itemServiceCommand = itemServiceCommand;
    }

    @Override
    public SaveItemCommandResponse handle(SaveItemCommand request) {
        return new SaveItemCommandResponse(itemServiceCommand.save(request.getItem()));
    }
}
