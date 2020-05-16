package net.repository;

import net.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository  extends JpaRepository<Item, Long> {
    List<Item> findAllByListId(Long id);
    Item findItemById(long id);
}
