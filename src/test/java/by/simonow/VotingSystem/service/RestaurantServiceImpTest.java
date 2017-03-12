package by.simonow.VotingSystem.service;

import by.simonow.VotingSystem.MealTestData;
import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.to.RestaurantWithVotes;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.simonow.VotingSystem.RestaurantTestData.*;

/**
 * Created by Алексей on 04.03.2017.
 */
public class RestaurantServiceImpTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void save() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void testGetWithMeals() throws Exception {
        Restaurant restaurant = service.getWithMeals(RESTAURANT1_ID);
        MATCHER.assertEquals(RESTAURANT1, restaurant);
        MealTestData.MATCHER.assertCollectionEquals(MealTestData.MEALS_R1, restaurant.getMeals());
    }

    @Test
    public void getAllGetWithVotes() throws Exception {
        List<RestaurantWithVotes> restaurantWithVotes = service.getAllWithVotes();
        MATCHER_WITH_VOTES.assertCollectionEquals(restaurantWithVotes,RESTAURANT_WITH_VOTES);
    }

    @Test
    public void update() throws Exception {

    }



}