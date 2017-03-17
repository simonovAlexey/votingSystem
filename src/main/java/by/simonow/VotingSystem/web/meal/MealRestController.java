package by.simonow.VotingSystem.web.meal;

import by.simonow.VotingSystem.model.Meal;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meals";

    @Override
    @GetMapping("/r={restId}/{id}")
    public Meal get(@PathVariable("id") int id,@PathVariable("restId") int restId) {
        return super.get(id,restId);
    }

    @Override
    @DeleteMapping("/r={restId}/{id}")
    public void delete(@PathVariable("id") int id,@PathVariable("restId") int restId) {
        super.delete(id,restId);
    }


    @Override
    @GetMapping(value = "/r={restId}")
    public List<Meal> getAll(@PathVariable("restId") int restId) {
        return super.getAll(restId);
    }

    @Override
    @PutMapping(value = "/r={restId}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Meal meal, @PathVariable("restId") int restId, @PathVariable("id") int id) {
        super.update(meal, id,restId);
    }

    @PostMapping(value = "/r={restId}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@Valid @RequestBody Meal meal,@PathVariable("restId") int restId) {
        Meal created = super.create(meal,restId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}