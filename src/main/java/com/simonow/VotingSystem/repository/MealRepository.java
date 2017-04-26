package com.simonow.VotingSystem.repository;

import com.simonow.VotingSystem.model.Dish;

import java.util.List;


public interface MealRepository {
     // null if updated restaurant do not belong to restaurantId
    Dish save(Dish dish, int restaurantId);

    // false if restaurant do not belong to restaurantId
    boolean delete(int id, int restaurantId);

    // null if restaurant do not belong to restaurantId
    Dish get(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);

    List<Dish> getMenu(int restaurantId);

    Dish getWithRestaurant(int id, int userId) ;
}
