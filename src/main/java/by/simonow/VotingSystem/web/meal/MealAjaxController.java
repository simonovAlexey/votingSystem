package by.simonow.VotingSystem.web.meal;

import by.simonow.VotingSystem.model.Dish;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/ajax/dishes")
public class MealAjaxController extends AbstractMealController {

    @Override
    @GetMapping(value = "/{restId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll(@PathVariable("restId") int restId) {
        return super.getAll(restId);
    }

    @Override
    @GetMapping(value = "/{restId}/{id}")
    public Dish get(@PathVariable("id") int id, @PathVariable("restId") int restId) {
        return super.get(id, restId);
    }

    @Override
    @DeleteMapping(value = "/{restId}/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("restId") int restId) {
        super.delete(id, restId);
    }

    @PostMapping(value = "/{restId}")
    public void updateOrCreate(@Valid Dish dish, @PathVariable("restId") int restId) {
        if (dish.isNew()) {
            super.create(dish, restId);
        } else {
            super.update(dish, dish.getId(), restId);
        }
    }

    @PostMapping(value = "/{restId}/{id}")
    public void enabled(@PathVariable("id") int id, @PathVariable("restId") int restId, @RequestParam("inMenu") boolean inMenu) {
        super.menuSelect(id, restId, inMenu);
    }
}
