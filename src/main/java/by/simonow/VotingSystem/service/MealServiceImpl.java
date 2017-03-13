package by.simonow.VotingSystem.service;

import by.simonow.VotingSystem.model.Meal;
import by.simonow.VotingSystem.repository.MealRepository;
import by.simonow.VotingSystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;

import static by.simonow.VotingSystem.util.ValidationUtil.checkNotFoundWithId;

@Service("mealService")
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal get(int id, int restaurantId)  {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @CacheEvict(value = "meals", allEntries = true)
    @Override
    public void delete(int id, int restaurantId)  {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    @Override
    public Collection<Meal> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Cacheable("meals")
    @Override
    public Collection<Meal> getMenu(int restaurantId) {
        return repository.getMenu(restaurantId);
    }

    @CacheEvict(value = "meals", allEntries = true)
    @Override
    public Meal update(Meal meal, int restaurantId) throws NotFoundException {
        Assert.notNull(meal, "meal must not be null");
        return checkNotFoundWithId(repository.save(meal, restaurantId), meal.getId());
    }

    @CacheEvict(value = "meals", allEntries = true)
    @Override
    public Meal save(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal, restaurantId);
    }

    @Override
    public Meal getWithRestaurant(int id, int restaurantId) {
        return checkNotFoundWithId(repository.getWithRestaurant(id, restaurantId), id);
    }

    @CacheEvict(value = "meals", allEntries = true)
    @Override
    public void evictCache() {
    }

}
