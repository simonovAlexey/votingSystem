package by.simonow.VotingSystem.service;

import by.simonow.VotingSystem.model.Dish;
import by.simonow.VotingSystem.util.exception.NotFoundException;

import java.util.List;


public interface DishService {

    Dish get(int id, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;

    List<Dish> getAll(int restaurantId);

    List<Dish> getMenu(int restaurantId);

    Dish update(Dish dish, int restaurantId) throws NotFoundException;

    Dish save(Dish dish, int restaurantId);

    Dish getWithRestaurant(int id, int restaurantId);

    void evictCache();

    void menuSelect(int id,int restId, boolean enabled);

}
