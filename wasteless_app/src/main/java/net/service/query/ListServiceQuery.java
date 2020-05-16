package net.service.query;

import net.model.Lists;
import net.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ListServiceQuery {

//    @Autowired
//    private ListRepository listRepository;

    private final ListRepository listRepository;

    public ListServiceQuery(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

//    public List<Lists> listAll() {
//        return listRepository.findAll();
//    }

    public List<Lists> findAllListsByUsername(String name) {
        return listRepository.findAllByUser_Username(name);
    }
    public Lists findListById(long id){
        return listRepository.findListsById(id);
    }

//    public List<Lists> findListsByUserId(Long userId) {
//        return listRepository.findAllByUserId(userId);
//    }
//
//    public Optional<Lists> findById(long id) {
//        return listRepository.findById(id);
//    }

}
