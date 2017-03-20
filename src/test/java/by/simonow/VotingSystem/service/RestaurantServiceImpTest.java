package by.simonow.VotingSystem.service;

import by.simonow.VotingSystem.MealTestData;
import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.to.RestaurantTo;
import by.simonow.VotingSystem.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static by.simonow.VotingSystem.RestaurantTestData.*;


public class RestaurantServiceImpTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void testSave() throws Exception {
        Restaurant newRest = getCreated();
        Restaurant created = service.save(newRest);
        newRest.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT1,RESTAURANT2,RESTAURANT3,newRest), service.getAll());

    }

    @Test
    public void testDelete() throws Exception {
        service.delete(RESTAURANT1_ID);
        Collection<Restaurant> restaurants = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT2,RESTAURANT3), restaurants);
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
    public void testGetWithMeals() throws Exception {
        Restaurant restaurant = service.getWithMeals(RESTAURANT1_ID);
        MATCHER.assertEquals(RESTAURANT1, restaurant);
        MealTestData.MATCHER.assertCollectionEquals(MealTestData.MEALS_R1, restaurant.getMeals());
    }

    @Test
    public void testGetWithVotes() throws Exception {
        List<RestaurantTo> restaurantWithVotes = service.getAllWithVotesMeals();
        MATCHER_WITH_VOTES.assertCollectionEquals(restaurantWithVotes,RESTAURANT_WITH_VOTES);
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant upd = getUpdated();
        service.update(upd);
        MATCHER_WITH_VOTES.assertEquals(new RestaurantTo(upd.getId(),upd.getName(),2), service.getWithVotes(RESTAURANT1_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(RESTAURANTS, service.getAll());
    }

    public void testValidation() throws Exception {
    //TODO Restaurant validation test
    }

}