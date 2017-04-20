package by.simonow.VotingSystem.web.json;

import by.simonow.VotingSystem.RestaurantTestData;
import by.simonow.VotingSystem.UserTestData;
import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.model.User;
import org.junit.Test;

import java.util.List;

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(RestaurantTestData.RESTAURANT1);
        System.out.println(json);
        Restaurant rest = JsonUtil.readValue(json, Restaurant.class);
        RestaurantTestData.MATCHER.assertEquals(RestaurantTestData.RESTAURANT1, rest);

        String json2 = JsonUtil.writeValue(UserTestData.ADMIN);
        System.out.println(json2);
        User rest2 = JsonUtil.readValue(json2, User.class);
        UserTestData.MATCHER.assertEquals(UserTestData.ADMIN, rest2);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(RestaurantTestData.RESTAURANTS);
        System.out.println(json);
        List<Restaurant> dishes = JsonUtil.readValues(json, Restaurant.class);
        RestaurantTestData.MATCHER.assertCollectionEquals(RestaurantTestData.RESTAURANTS, dishes);
    }
}