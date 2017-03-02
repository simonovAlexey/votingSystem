package by.simonow.VotingSystem.repository.datajpa;


import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.Collection;

public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository{
    private static final Sort SORT_VOTES = new Sort("votes");

    @Autowired
    private CrudRestaurantRepository crudRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return crudRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRepository.findOne(id);
    }

    @Override
    public Collection<Restaurant> getAll() {
        return crudRepository.getAll(SORT_VOTES);
    }

    @Override
    public Restaurant getWithMeals(int id) {
        return crudRepository.getWithMeals(id);
    }
}
