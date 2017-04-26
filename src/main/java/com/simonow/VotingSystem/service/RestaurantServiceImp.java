package com.simonow.VotingSystem.service;

import com.simonow.VotingSystem.model.Restaurant;
import com.simonow.VotingSystem.repository.RestaurantRepository;
import com.simonow.VotingSystem.to.RestaurantTo;
import com.simonow.VotingSystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.simonow.VotingSystem.util.ValidationUtil.checkNotFoundWithId;


@Service("restaurantService")
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public RestaurantTo getWithVotes(int id) throws NotFoundException {
        Restaurant rest = checkNotFoundWithId(repository.getWithVotes(id), id);
        return new RestaurantTo(rest.getId(),rest.getName(),rest.getVotes().size());
    }

    @Override
    public RestaurantTo getTo(int id) throws NotFoundException {
        Restaurant rest = checkNotFoundWithId(repository.get(id), id);
        return new RestaurantTo(rest.getId(),rest.getName(),0);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public List<RestaurantTo> getAllWithVotes() {
        return repository.getAllRestaurantTo();
        /*return repository.getAllWithVotes().stream().
                map((s) -> new RestaurantTo(s.getId(), s.getName(), s.getVotes().size())).
                collect(Collectors.toList());*/
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        repository.save(restaurant);
    }

    @Override
    public Restaurant getWithDishes(int id) {
        return repository.getWithDishes(id);
    }

}
