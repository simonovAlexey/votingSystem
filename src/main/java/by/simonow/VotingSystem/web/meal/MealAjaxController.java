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
    @GetMapping(value = "/rest/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getAll(@PathVariable("id") int restId) {
        return super.getAll(restId);
    }

    @Override
    @GetMapping(value = "/{id}")
    public Meal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void updateOrCreate(@Valid Meal meal,@RequestParam("rest") int restId) {
        if (meal.isNew()) {
            super.create(meal,restId);
        } else {
            super.update(meal, meal.getId(),restId);
        }
    }
}
