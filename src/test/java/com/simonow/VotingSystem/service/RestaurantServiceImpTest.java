package com.simonow.VotingSystem.service;

import com.simonow.VotingSystem.MealTestData;
import com.simonow.VotingSystem.model.Restaurant;
import com.simonow.VotingSystem.to.RestaurantTo;
import com.simonow.VotingSystem.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static com.simonow.VotingSystem.MealTestData.DISH_2;
import static com.simonow.VotingSystem.MealTestData.DISH_3;
import static com.simonow.VotingSystem.RestaurantTestData.*;


public class RestaurantServiceImpTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void testSave() throws Exception {
        Restaurant newRest = getCreated();
        Restaurant created = service.save(newRest);
        newRest.setId(created.getId());
        RestaurantTo newRestTO = new RestaurantTo(newRest.getId(),newRest.getName(),0);

        MATCHER_WITH_VOTES.assertCollectionEquals(service.getAllWithVotes(),
                Arrays.asList(REST_WITH_VOTES1,REST_WITH_VOTES2,REST_WITH_VOTES3,newRestTO));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(RESTAURANT1_ID);
        List<RestaurantTo> restaurantWithVotes = service.getAllWithVotes();
        MATCHER_WITH_VOTES.assertCollectionEquals(restaurantWithVotes,Arrays.asList(REST_WITH_VOTES2,REST_WITH_VOTES3));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(RESTAURANT1_ID+3);
    }

    @Test
    public void testGet() throws Exception {
        RestaurantTo actual = service.getWithVotes(RESTAURANT1_ID);
        MATCHER_WITH_VOTES.assertEquals(REST_WITH_VOTES1, actual);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.getWithVotes(RESTAURANT1_ID+5);
    }

    @Test
    public void testGetWithDishes() throws Exception {
        Restaurant restaurant = service.getWithDishes(RESTAURANT1_ID);
        MATCHER.assertEquals(RESTAURANT1, restaurant);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MealTestData.DISH_1, DISH_3, DISH_2), restaurant.getDishes());
    }

    @Test
    public void testGetWithVotes() throws Exception {
        List<RestaurantTo> restaurantWithVotes = service.getAllWithVotes();
        MATCHER_WITH_VOTES.assertCollectionEquals(restaurantWithVotes,RESTAURANT_WITH_VOTES);
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant upd = getUpdated();
        service.update(upd);
        MATCHER_WITH_VOTES.assertEquals(new RestaurantTo(upd.getId(),upd.getName(),2), service.getWithVotes(RESTAURANT1_ID));
    }


    public void testValidation() throws Exception {
    //TODO Restaurant validation test
    }

}