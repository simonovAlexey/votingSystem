package by.simonow.VotingSystem.repository.datajpa;


import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.repository.RestaurantRepository;
import by.simonow.VotingSystem.to.RestaurantWithVotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    public Collection<Restaurant> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public Restaurant getWithMeals(int id) {
        return crudRepository.getWithMeals(id);
    }

    @Override
    public List<RestaurantWithVotes> getAllWithVotes() {
        List<Restaurant> allWithVotesSet = crudRepository.getAllWithVotes();
        return allWithVotesSet.stream().
                map((s) -> new RestaurantWithVotes(s.getId(), s.getName(), s.getVotes().size())).
                collect(Collectors.toList());
    }
}
