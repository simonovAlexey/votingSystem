package by.simonow.VotingSystem.web.restaurant;

import by.simonow.VotingSystem.AuthorizedUser;
import by.simonow.VotingSystem.model.Meal;
import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.model.User;
import by.simonow.VotingSystem.service.MealService;
import by.simonow.VotingSystem.service.RestaurantService;
import by.simonow.VotingSystem.service.UserService;
import by.simonow.VotingSystem.service.VoteService;
import by.simonow.VotingSystem.to.RestaurantWithVotes;
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
    private MealService mealService;


    public Restaurant get(int id) {
        LOG.info("get restaurant id=", id);
        return service.get(id);
    }

    public void delete(int id) {
        LOG.info("delete restaurant id=", id);
        service.delete(id);
    }

    public List<Restaurant> getAll() {
        int userId = AuthorizedUser.id();
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
        int userId = AuthorizedUser.id();
        LOG.info("create {} for User {}", rest, userId);
        return service.save(rest);
    }

    public List<RestaurantWithVotes> getAllWithVotes(){
        LOG.info("getAllWithVotes() ");
        return service.getAllWithVotes();
    }

    public void doVote(Restaurant rest, int id){
        checkIdConsistent(rest, id);
        User user = userService.get(AuthorizedUser.id());
        LOG.info("doVote {} from User {}", rest, user);
        voteService.vote(rest,user);

    }

    public List<Meal> getMenu(Restaurant rest, int id) {
        checkIdConsistent(rest, id);
        LOG.info("getMenu for Restaurant {}", rest);
        return mealService.getMenu(rest.getId());
    }

}