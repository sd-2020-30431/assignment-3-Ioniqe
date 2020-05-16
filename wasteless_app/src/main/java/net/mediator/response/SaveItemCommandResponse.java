package net.mediator.response;

import net.mediator.interfaces.Response;
import net.model.Item;

public class SaveItemCommandResponse implements Response {
    private Item item;

    public SaveItemCommandResponse(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
