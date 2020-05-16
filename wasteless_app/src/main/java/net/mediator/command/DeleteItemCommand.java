package net.mediator.command;

import net.mediator.interfaces.Request;

public class DeleteItemCommand implements Request {
    private long id;

    public DeleteItemCommand(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
