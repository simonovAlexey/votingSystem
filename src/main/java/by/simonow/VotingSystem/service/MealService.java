package by.simonow.VotingSystem.service;

import by.simonow.VotingSystem.model.Meal;
import by.simonow.VotingSystem.util.exception.NotFoundException;

import java.util.List;


public interface MealService {

    Meal get(int id, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;

    List<Meal> getAll(int restaurantId);

    List<Meal> getMenu(int restaurantId);

    Meal update(Meal meal, int restaurantId) throws NotFoundException;

    Meal save(Meal meal, int restaurantId);

    Meal getWithRestaurant(int id, int restaurantId);

    void evictCache();

    void menuSelect(int id,int restId, boolean enabled);

}
