package net.mediator.command;

import net.mediator.interfaces.Request;
import net.model.Lists;

public class SaveListCommand implements Request {
    private Lists list;

    public SaveListCommand(Lists list) {
        this.list = list;
    }

    public Lists getList() {
        return list;
    }

    public void setList(Lists list) {
        this.list = list;
    }
}
