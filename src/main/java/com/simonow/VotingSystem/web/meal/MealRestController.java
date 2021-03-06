package com.simonow.VotingSystem.web.meal;

import com.simonow.VotingSystem.model.Dish;
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
    static final String REST_URL = "/rest/dishes";

    @Override
    @GetMapping("/{restId}/{id}")
    public Dish get(@PathVariable("id") int id, @PathVariable("restId") int restId) {
        return super.get(id,restId);
    }

    @Override
    @DeleteMapping("/{restId}/{id}")
    public void delete(@PathVariable("id") int id,@PathVariable("restId") int restId) {
        super.delete(id,restId);
    }


    @Override
    @GetMapping(value = "/{restId}")
    public List<Dish> getAll(@PathVariable("restId") int restId) {
        return super.getAll(restId);
    }

    @Override
    @PutMapping(value = "/{restId}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Dish dish, @PathVariable("restId") int restId, @PathVariable("id") int id) {
        super.update(dish, id,restId);
    }

    @PostMapping(value = "/{restId}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish, @PathVariable("restId") int restId) {
        Dish created = super.create(dish,restId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}