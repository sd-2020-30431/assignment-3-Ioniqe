package net.mediator.response;

import net.mediator.interfaces.Response;
import net.model.Item;


public class FindItemQueryResponse implements Response {
    private Item item;

    public FindItemQueryResponse(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
