package by.simonow.VotingSystem.repository.datajpa;

import by.simonow.VotingSystem.model.Meal;
import by.simonow.VotingSystem.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository crudMealRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            return null;
        }
        meal.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudMealRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public Meal get(int id, int restaurantId) {
        Meal meal = crudMealRepository.findOne(id);
        return meal != null && meal.getRestaurant().getId() == restaurantId ? meal : null;
    }

    @Override
    public List<Meal> getAll(int restaurantId) {
        return crudMealRepository.getAll(restaurantId);
    }

    @Override
    public List<Meal> getInmenu(int restaurantId) {
        return crudMealRepository.getMenu(restaurantId);
    }

    @Override
    public Meal getWithRestaurant(int id, int userId) {
        return crudMealRepository.getWithRestaurant(id, userId);
    }
}
