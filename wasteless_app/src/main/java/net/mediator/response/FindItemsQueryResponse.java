package net.mediator.response;

import net.mediator.interfaces.Response;
import net.model.Item;

import java.util.List;

public class FindItemsQueryResponse implements Response {
    private List<Item> items;

    public FindItemsQueryResponse(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
