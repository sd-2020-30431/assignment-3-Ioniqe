package net.mediator.response;

import net.mediator.interfaces.Response;
import net.model.Lists;

import java.util.List;

public class FindListsQueryResponse implements Response {
    private List<Lists> lists;

    public FindListsQueryResponse(List<Lists> lists) {
        this.lists = lists;
    }

    public List<Lists> getLists() {
        return lists;
    }

    public void setLists(List<Lists> lists) {
        this.lists = lists;
    }
}
