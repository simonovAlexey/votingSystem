package repository;


import model.Meal;
import model.Restaurant;

import java.util.Collection;


public interface RestaurantRepository {
    // null if updated meal do not belong to userId
    Meal save(Restaurant restaurant);

    // false if meal do not belong to userId
    boolean delete(int id);

    // null if meal do not belong to userId
    Restaurant get(int id);

    // ORDERED description
    Collection<Restaurant> getAll();
}
