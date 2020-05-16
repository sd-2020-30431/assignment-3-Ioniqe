package net.mediator.response;

import net.mediator.interfaces.Response;
import net.model.Lists;

public class SaveListCommandResponse implements Response {
    private Lists list;

    public SaveListCommandResponse(Lists list) {
        this.list = list;
    }

    public Lists getList() {
        return list;
    }

    public void setList(Lists list) {
        this.list = list;
    }
}
