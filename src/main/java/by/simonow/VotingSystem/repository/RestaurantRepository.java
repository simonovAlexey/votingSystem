package by.simonow.VotingSystem.repository;


import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.to.RestaurantWithVotes;

import java.util.Collection;
import java.util.List;


public interface RestaurantRepository {
    // null if updated meal do not belong to userId
    Restaurant save(Restaurant restaurant);

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    Restaurant get(int id);

    // ORDERED description
    Collection<Restaurant> getAll();

    Restaurant getWithMeals(int id);

    List<RestaurantWithVotes> getAllWithVotes();
}
