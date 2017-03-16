package by.simonow.VotingSystem.repository;

import by.simonow.VotingSystem.model.Meal;

import java.util.List;


public interface MealRepository {
     // null if updated restaurant do not belong to restaurantId
    Meal save(Meal meal, int restaurantId);

    // false if restaurant do not belong to restaurantId
    boolean delete(int id, int restaurantId);

    // null if restaurant do not belong to restaurantId
    Meal get(int id, int restaurantId);

    List<Meal> getAll(int restaurantId);

    List<Meal> getMenu(int restaurantId);

    Meal getWithRestaurant(int id, int userId) ;
}
