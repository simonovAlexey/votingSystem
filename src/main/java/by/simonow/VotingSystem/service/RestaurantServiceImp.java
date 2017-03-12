package by.simonow.VotingSystem.service;

import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.repository.RestaurantRepository;
import by.simonow.VotingSystem.to.RestaurantWithVotes;
import by.simonow.VotingSystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

import static by.simonow.VotingSystem.util.ValidationUtil.checkNotFoundWithId;


@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public Collection<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public List<RestaurantWithVotes> getAllWithWotes() {
        return repository.getAllWithVotes();
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        repository.save(restaurant);
    }

    @Override
    public Restaurant getWithMeals(int id) {
        return repository.getWithMeals(id);
    }


}
