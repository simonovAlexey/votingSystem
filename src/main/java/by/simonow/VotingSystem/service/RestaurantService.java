package by.simonow.VotingSystem.service;


import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.to.RestaurantTo;
import by.simonow.VotingSystem.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant save(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    RestaurantTo getWithVotes(int id) throws NotFoundException;

    RestaurantTo getTo(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    List<Restaurant> getAll();

    List<RestaurantTo> getAllWithVotes();

    void update(Restaurant restaurant);

    Restaurant getWithMeals(int id);
}
