package by.simonow.VotingSystem.service;


import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.to.RestaurantWithVotes;
import by.simonow.VotingSystem.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant save(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    RestaurantWithVotes getWithVotes(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    List<Restaurant> getAll();

    List<RestaurantWithVotes> getAllWithVotes();

    void update(Restaurant restaurant);

    Restaurant getWithMeals(int id);
}
