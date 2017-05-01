package com.simonow.VotingSystem.service;


import com.simonow.VotingSystem.model.Restaurant;
import com.simonow.VotingSystem.to.RestaurantTo;
import com.simonow.VotingSystem.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant save(Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    RestaurantTo getWithVotes(int id) throws NotFoundException;

    RestaurantTo getTo(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    List<RestaurantTo> getAllWithVotes();

    void update(Restaurant restaurant);

    Restaurant getWithDishes(int id);
}
