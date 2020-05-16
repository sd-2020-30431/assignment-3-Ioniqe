package net.mediator.query;

import net.mediator.interfaces.Request;

public class ItemsQuery implements Request {
    private Long listId;

    public ItemsQuery(Long listId) {
        this.listId = listId;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

}
