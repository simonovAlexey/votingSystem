package by.simonow.VotingSystem.web.restaurant;

import by.simonow.VotingSystem.AuthorizedUser;
import by.simonow.VotingSystem.model.Dish;
import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.model.User;
import by.simonow.VotingSystem.model.Votes;
import by.simonow.VotingSystem.service.DishService;
import by.simonow.VotingSystem.service.RestaurantService;
import by.simonow.VotingSystem.service.UserService;
import by.simonow.VotingSystem.service.VoteService;
import by.simonow.VotingSystem.to.RestaurantTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.simonow.VotingSystem.util.ValidationUtil.checkIdConsistent;
import static by.simonow.VotingSystem.util.ValidationUtil.checkNew;


public abstract class AbstractRestaurantController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRestaurantController.class);

    @Autowired
    private RestaurantService service;

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    @Autowired
    private DishService dishService;


    public RestaurantTo getTo(int id) {
        LOG.info("getTo restaurant id=", id);
        return service.getTo(id);
    }

    public Restaurant getWithDishes(int id) {
        LOG.info("getWithDishes restaurant id=", id);
        return service.getWithDishes(id);
    }

    public void delete(int id) {
        LOG.info("delete restaurant id=", id);
        service.delete(id);
    }

    public List<Restaurant> getAll() {
        LOG.info("getAll ");
        return service.getAll();
    }

    public void update(Restaurant rest, int id) {
        checkIdConsistent(rest, id);
        LOG.info("update {}", rest);
        service.update(rest);
    }

    public Restaurant create(Restaurant rest) {
        checkNew(rest);
        LOG.info("create {}", rest);
        return service.save(rest);
    }

    public List<RestaurantTo> getAllWithVotes(){
        LOG.info("getAllWithVotes() ");
        return service.getAllWithVotes();
    }

    public void doVote(int restId){
        User user = userService.get(AuthorizedUser.id());
        Restaurant rest = service.get(restId);
        LOG.info("doVote {} from User {}", restId, user);
        Votes vote = voteService.vote(rest, user);
        AuthorizedUser.get().getUserTo().setTodayVote(vote);

    }

    public List<Dish> getMenu(int id) {
        LOG.info("getMenuList for Restaurant {}", id);
        return dishService.getMenu(id);
    }

}