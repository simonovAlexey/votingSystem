package by.simonow.VotingSystem.service;

import by.simonow.VotingSystem.model.Dish;
import by.simonow.VotingSystem.repository.MealRepository;
import by.simonow.VotingSystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static by.simonow.VotingSystem.util.ValidationUtil.checkNotFoundWithId;

@Service("dishService")
public class DishServiceImpl implements DishService {

    @Autowired
    private MealRepository repository;

    @Override
    public Dish get(int id, int restaurantId)  {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public void delete(int id, int restaurantId)  {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Cacheable("dishes")
    @Override
    public List<Dish> getMenu(int restaurantId) {
        return repository.getMenu(restaurantId);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public Dish update(Dish dish, int restaurantId) throws NotFoundException {
        Assert.notNull(dish, "restaurant must not be null");
        return checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public Dish save(Dish dish, int restaurantId) {
        Assert.notNull(dish, "restaurant must not be null");
        return repository.save(dish, restaurantId);
    }

    @Override
    public Dish getWithRestaurant(int id, int restaurantId) {
        return checkNotFoundWithId(repository.getWithRestaurant(id, restaurantId), id);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public void evictCache() {
    }

    @Override
    @Transactional
    public void menuSelect(int id,int restId, boolean enabled) {
        Dish dish = get(id, restId);
        dish.setInMenu(enabled);
        repository.save(dish,restId);
    }

}
