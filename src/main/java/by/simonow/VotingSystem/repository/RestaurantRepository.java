package by.simonow.VotingSystem.repository;


import by.simonow.VotingSystem.model.Restaurant;

import java.util.List;


public interface RestaurantRepository {
    // null if updated restaurant do not belong to userId
    Restaurant save(Restaurant restaurant);

    // false if restaurant do not belong to userId
    boolean delete(int id);

    // null if restaurant do not belong to userId
    Restaurant get(int id);

    Restaurant getWithVotes(int id);

    List<Restaurant> getAll();

    Restaurant getWithDishes(int id);

    List<Restaurant> getAllWithVotes();
}
