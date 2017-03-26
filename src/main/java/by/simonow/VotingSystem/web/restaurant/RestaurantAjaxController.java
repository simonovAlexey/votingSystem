package by.simonow.VotingSystem.web.restaurant;

import by.simonow.VotingSystem.AuthorizedUser;
import by.simonow.VotingSystem.model.Meal;
import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.to.RestaurantTo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/ajax/restaurants")
public class RestaurantAjaxController extends AbstractRestaurantController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getAllWithVotes() {
        return super.getAllWithVotes();
    }

    @Override
    @GetMapping(value = "/{id}")
    public RestaurantTo getTo(@PathVariable("id") int id) {
        return super.getTo(id);
    }

    @GetMapping(value = "/vRest")
    public String getVotedResaurant() {
        return AuthorizedUser.get().getUserTo().getTodayVote().getRestaurant().getName();
    }

    @Override
    @GetMapping(value = "/menu={id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getMenu(@PathVariable("id") int id) {
        return super.getMenu(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void updateOrCreate(@Valid Restaurant rest) {
        if (rest.isNew()) {
            super.create(rest);
        } else {
            super.update(rest, rest.getId());
        }
    }

    @Override
    @PostMapping(value = "/v")
    public void doVote(@RequestParam("id") int restId){
        super.doVote(restId);

    }
}
