package by.simonow.VotingSystem.repository;

import by.simonow.VotingSystem.model.Meal;

import java.util.Collection;

/**
 * Created by Алексей on 09.01.2017.
 */
public interface MealRepository {
     // null if updated meal do not belong to restaurantId
    Meal save(Meal meal, int restaurantId);

    // false if meal do not belong to restaurantId
    boolean delete(int id, int restaurantId);

    // null if meal do not belong to restaurantId
    Meal get(int id, int restaurantId);

    Collection<Meal> getAll(int restaurantId);

    Collection<Meal> getMenu(int restaurantId);

    Meal getWithRestaurant(int id, int userId) ;
}
