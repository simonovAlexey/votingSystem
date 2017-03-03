package by.simonow.VotingSystem.service;

import by.simonow.VotingSystem.model.Meal;
import by.simonow.VotingSystem.util.exception.NotFoundException;

import java.util.Collection;


public interface MealService {

    Meal get(int id, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;

    Collection<Meal> getAll(int restaurantId);

    Collection<Meal> getMenu(int restaurantId);

    Meal update(Meal meal, int restaurantId) throws NotFoundException;

    Meal save(Meal meal, int restaurantId);

    Meal getWithRestaurant(int id, int restaurantId);

    void evictCache();

}
