package by.simonow.VotingSystem.repository.datajpa;

import by.simonow.VotingSystem.model.Dish;
import by.simonow.VotingSystem.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudDishRepository crudDishRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudDishRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        Dish dish = crudDishRepository.findOne(id);
        return dish != null && dish.getRestaurant().getId() == restaurantId ? dish : null;
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return crudDishRepository.getAllDishes(restaurantId);
    }

    @Override
    public List<Dish> getMenu(int restaurantId) {
        return crudDishRepository.getMenu(restaurantId);
    }

    @Override
    public Dish getWithRestaurant(int id, int userId) {
        return crudDishRepository.getWithRestaurant(id, userId);
    }
}
