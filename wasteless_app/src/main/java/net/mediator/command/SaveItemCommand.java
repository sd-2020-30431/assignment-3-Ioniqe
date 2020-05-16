package net.mediator.command;

import net.mediator.interfaces.Request;
import net.model.Item;

public class SaveItemCommand implements Request {

    private Item item;

    public SaveItemCommand(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
