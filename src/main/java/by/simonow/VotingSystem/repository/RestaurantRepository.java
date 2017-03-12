package by.simonow.VotingSystem.repository;


import by.simonow.VotingSystem.model.Restaurant;

import java.util.Collection;


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

    Restaurant getWithVotes(int id);
}
