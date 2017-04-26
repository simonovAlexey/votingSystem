package com.simonow.VotingSystem;


import com.simonow.VotingSystem.matcher.ModelMatcher;
import com.simonow.VotingSystem.model.Restaurant;
import com.simonow.VotingSystem.to.RestaurantTo;
import com.simonow.VotingSystem.model.BaseEntity;

import java.util.Arrays;
import java.util.List;

public class RestaurantTestData {
    public static final ModelMatcher<Restaurant> MATCHER = ModelMatcher.of(Restaurant.class);
    public static final ModelMatcher<RestaurantTo> MATCHER_WITH_VOTES = ModelMatcher.of(RestaurantTo.class);

    public static final int RESTAURANT1_ID = BaseEntity.START_SEQ;
    public static final int RESTAURANT2_ID = BaseEntity.START_SEQ + 1;
    public static final int RESTAURANT3_ID = BaseEntity.START_SEQ + 2;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Mak", MealTestData.DISHES_R_1);
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID+1, "Плакучая ива", MealTestData.DISHES_R_2);
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID+2, "Столовая", MealTestData.DISHES_R_3);
    public static final RestaurantTo REST_WITH_VOTES1 = new RestaurantTo(RESTAURANT1_ID, "Mak",  2);
    public static final RestaurantTo REST_WITH_VOTES2 = new RestaurantTo(RESTAURANT1_ID+1, "Плакучая ива",  7);
    public static final RestaurantTo REST_WITH_VOTES3 = new RestaurantTo(RESTAURANT1_ID+2, "Столовая",  4);

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3);
    public static final List<RestaurantTo> RESTAURANT_WITH_VOTES = Arrays.asList(REST_WITH_VOTES1,REST_WITH_VOTES2,REST_WITH_VOTES3);

    public static Restaurant getCreated() {
        return new Restaurant(null, "Созданный ресторан",  null);
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Обновленный Р1 Mak",  null);
    }
}
