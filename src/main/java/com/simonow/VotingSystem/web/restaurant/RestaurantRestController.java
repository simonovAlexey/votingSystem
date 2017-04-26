package com.simonow.VotingSystem.web.restaurant;

import com.simonow.VotingSystem.model.Dish;
import com.simonow.VotingSystem.model.Restaurant;
import com.simonow.VotingSystem.to.RestaurantTo;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/restaurants";

    @Override
    @GetMapping(value = "/{id}")
    public RestaurantTo getTo(@PathVariable("id") int id) {
        return super.getTo(id);
    }

    @Override
    @GetMapping(value = "/menu={id}")
    public List<Dish> getMenu(@PathVariable("id") int id) {
        return super.getMenu(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<RestaurantTo> getAllWithVotes() {
        return super.getAllWithVotes();
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Restaurant rest, @PathVariable("id") int id) {
        super.update(rest, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@Valid @RequestBody Restaurant rest) {
        Restaurant created = super.create(rest);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PostMapping(value = "/vote={id}")
    public void doVote(@PathVariable("id") int restId) {
        super.doVote(restId);
    }
}