package by.simonow.VotingSystem.repository.datajpa;


import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.repository.RestaurantRepository;
import by.simonow.VotingSystem.to.RestaurantTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepositoryImpl implements RestaurantRepository {

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
    public Restaurant getWithVotes(int id) {
        return crudRepository.getWithVotes(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public Restaurant getWithDishes(int id) {
        return crudRepository.getWithDishes(id);
    }

    @Override
    public List<Restaurant> getAllWithVotes() {
        return  crudRepository.getAllWithVotes();
    }

    @Override
    public List<RestaurantTo> getAllRestaurantTo() {
        return crudRepository.getAllRestaurantTo();
    }
}
