package net.service.query;

import net.model.Item;
import net.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceQuery {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> listAll() {
        return itemRepository.findAll();
    }

    public List<Item> findItems(Long id) {
        return itemRepository.findAllByListId(id);
    }

    public Item getById(long id){return itemRepository.findById(id).get();}

    public Item getItemById(long id){return itemRepository.findItemById(id);}
}
