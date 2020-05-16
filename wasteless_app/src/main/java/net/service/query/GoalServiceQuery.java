package net.service.query;

import net.model.Item;
import net.model.Lists;
import net.model.User;
import net.repository.ItemRepository;
import net.repository.ListRepository;
import net.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GoalServiceQuery {

    private final UserRepository userRepository;
    private final ListRepository listRepository;
    private final ItemRepository itemRepository;


    public GoalServiceQuery(UserRepository userRepository, ListRepository listRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.listRepository = listRepository;
        this.itemRepository = itemRepository;
    }

    public int calculateAmountOfDailyCalories(String username) {//long userId
        List<Lists> usersLists = listRepository.findAllByUser_Username(username);
        List<Item> itemsInTheList;

        int amount = 0;
        Date date = new Date();
        int days;

        for (Lists list : usersLists) {
            itemsInTheList = itemRepository.findAllByListId(list.getId());

            for (Item item : itemsInTheList) {

                if (item.getConsumptionDate() == null && item.getExpirationDate().after(date)) {
                    days = (int) ((item.getExpirationDate().getTime() - date.getTime()) / 86400000);
                    days++;
                    amount += item.getCalories() / days;
                }
            }
        }

        return amount;
    }

    public User getUserOfGoal(long userId) {
        return userRepository.findUserById(userId);
    }

    public User getUserByUsernameOfGoal(String username){
        return userRepository.findUserByUsername(username);
    }
}
