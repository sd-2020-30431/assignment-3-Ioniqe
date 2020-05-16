package net.service.command;

import net.model.Item;
import net.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceCommand {
    private final ItemRepository itemRepository;

    public ItemServiceCommand(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void delete(long id) {
        itemRepository.deleteById(id);
    }

}
