package net.mediator.query;

import net.mediator.interfaces.Request;

public class ItemQuery implements Request {
    private long itemId;

    public ItemQuery(long itemId) {
        this.itemId = itemId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
