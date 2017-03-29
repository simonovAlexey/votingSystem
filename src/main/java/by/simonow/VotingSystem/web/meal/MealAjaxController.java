package by.simonow.VotingSystem.web.meal;

import by.simonow.VotingSystem.model.Meal;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/ajax/meals")
public class MealAjaxController extends AbstractMealController {

    @Override
    @GetMapping(value = "/{restId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Meal> getAll(@PathVariable("restId") int restId) {
        return super.getAll(restId);
    }

    @Override
    @GetMapping(value = "/{restId}/{id}")
    public Meal get(@PathVariable("id") int id, @PathVariable("restId") int restId) {
        return super.get(id, restId);
    }

    @Override
    @DeleteMapping(value = "/{restId}/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("restId") int restId) {
        super.delete(id, restId);
    }

    @PostMapping(value = "/{restId}")
    public void updateOrCreate(@Valid Meal meal, @PathVariable("restId") int restId) {
        if (meal.isNew()) {
            super.create(meal, restId);
        } else {
            super.update(meal, meal.getId(), restId);
        }
    }

    @PostMapping(value = "/{restId}/{id}")
    public void enabled(@PathVariable("id") int id, @PathVariable("restId") int restId, @RequestParam("inMenu") boolean inMenu) {
        super.menuSelect(id, restId, inMenu);
    }
}
