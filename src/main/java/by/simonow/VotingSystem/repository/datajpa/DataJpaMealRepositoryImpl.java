package by.simonow.VotingSystem.repository.datajpa;

import by.simonow.VotingSystem.model.Meal;
import by.simonow.VotingSystem.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository crudMealRepository;

    @Override
    public Meal save(Meal meal, int restaurantId) {
        //TODO meal repo
        return null;
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return false;
    }

    @Override
    public Meal get(int id, int restaurantId) {
        return null;
    }

    @Override
    public Collection<Meal> getAll(int restaurantId) {
        return null;
    }

    @Override
    public Collection<Meal> getInmenu(int restaurantId) {
        return null;
    }

    @Override
    public Meal getWithRestaurant(int id, int userId) {
        return crudMealRepository.getWithRestaurant(id, userId);
    }
}
