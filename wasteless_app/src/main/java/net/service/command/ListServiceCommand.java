package net.service.command;

import net.model.Lists;
import net.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ListServiceCommand {

    private final ListRepository listRepository;

    public ListServiceCommand(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    public Lists save(Lists list) {
        return listRepository.save(list);
    }

    public void delete(long id) {
        listRepository.deleteById(id);
    }

}
