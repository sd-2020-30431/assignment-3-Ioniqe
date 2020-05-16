package net.mediator.command;

import net.mediator.interfaces.Request;

public class DeleteListCommand implements Request {
    private long id;

    public DeleteListCommand(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
