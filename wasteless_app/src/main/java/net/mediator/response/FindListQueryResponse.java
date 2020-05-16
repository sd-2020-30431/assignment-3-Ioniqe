package net.mediator.response;

import net.mediator.interfaces.Response;
import net.model.Lists;

public class FindListQueryResponse implements Response {
    private Lists list;

    public FindListQueryResponse(Lists list) {
        this.list = list;
    }

    public Lists getList() {
        return list;
    }

    public void setList(Lists list) {
        this.list = list;
    }
}
