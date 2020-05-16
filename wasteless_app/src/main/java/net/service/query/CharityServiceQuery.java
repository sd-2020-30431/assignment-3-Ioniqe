package net.service.query;

import net.model.Charity;
import net.repository.CharityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharityServiceQuery {

    private CharityRepository charityRepository;

    public CharityServiceQuery(CharityRepository charityRepository) {
        this.charityRepository = charityRepository;
    }

    public List<Charity> listAll() {
        return charityRepository.findAll();
    }
}
