package net.mediator.query;

import net.mediator.interfaces.Request;

public class ListQuery implements Request {

    private long listId;
    private String username;

    public ListQuery(long listId, String username) {
        this.listId = listId;
        this.username = username;
    }

    public ListQuery(String username) {
        this.username = username;
    }

    public ListQuery(long listId) {
        this.listId = listId;
    }

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
