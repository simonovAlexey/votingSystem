package by.simonow.VotingSystem.web.meal;

import by.simonow.VotingSystem.model.Meal;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/ajax/profile/meals")
public class MealAjaxController extends AbstractMealController {

    @Override
    @GetMapping(value = "/r/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getAll(@PathVariable("id") int restId) {
        return super.getAll(restId);
    }

    @Override
    @GetMapping(value = "/r/{restId}/{id}")
    public Meal get(@PathVariable("id") int id, @PathVariable("restId") int restId) {
        return super.get(id,restId);
    }

    @Override
    @DeleteMapping(value = "/r/{restId}/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("restId") int restId) {
        super.delete(id,restId);
    }

    @PostMapping
    public void updateOrCreate(@Valid Meal meal,@RequestParam("restId") int restId) {
        if (meal.isNew()) {
            super.create(meal,restId);
        } else {
            super.update(meal, meal.getId(),restId);
        }
    }
}
