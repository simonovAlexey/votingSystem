package by.simonow.VotingSystem.service;


import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.to.RestaurantWithVotes;
import by.simonow.VotingSystem.util.exception.NotFoundException;

import java.util.Collection;
import java.util.List;

public interface RestaurantService {

    Restaurant save(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    Collection<Restaurant> getAll();
    List<RestaurantWithVotes> getAllWithWotes();

    void update(Restaurant restaurant);

    Restaurant getWithMeals(int id);
}
