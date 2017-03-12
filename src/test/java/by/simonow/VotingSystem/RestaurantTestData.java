package by.simonow.VotingSystem;


import by.simonow.VotingSystem.matcher.ModelMatcher;
import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.to.RestaurantWithVotes;

import java.util.Arrays;
import java.util.List;

import static by.simonow.VotingSystem.MealTestData.MEALS_R1;
import static by.simonow.VotingSystem.MealTestData.MEALS_R2;
import static by.simonow.VotingSystem.MealTestData.MEALS_R3;
import static by.simonow.VotingSystem.model.BaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final ModelMatcher<Restaurant> MATCHER = ModelMatcher.of(Restaurant.class);
    public static final ModelMatcher<RestaurantWithVotes> MATCHER_WITH_VOTES = ModelMatcher.of(RestaurantWithVotes.class);

    public static final int RESTAURANT1_ID = START_SEQ;
    public static final int RESTAURANT2_ID = START_SEQ + 1;
    public static final int RESTAURANT3_ID = START_SEQ + 2;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Р1 Mak",  MEALS_R1);
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID+1, "Р2 Плакучая ива",  MEALS_R2);
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID+2, "Р3 Бистро",  MEALS_R3);
    public static final RestaurantWithVotes REST_WITH_VOTES1 = new RestaurantWithVotes(RESTAURANT1_ID, "Р1 Mak",  2);
    public static final RestaurantWithVotes REST_WITH_VOTES2 = new RestaurantWithVotes(RESTAURANT1_ID+1, "Р2 Плакучая ива",  7);
    public static final RestaurantWithVotes REST_WITH_VOTES3 = new RestaurantWithVotes(RESTAURANT1_ID+2, "Р3 Бистро",  4);

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT2, RESTAURANT3, RESTAURANT1);
    public static final List<RestaurantWithVotes> RESTAURANT_WITH_VOTES = Arrays.asList(REST_WITH_VOTES1,REST_WITH_VOTES2,REST_WITH_VOTES3);

    public static Restaurant getCreated() {
        return new Restaurant(null, "Созданный ресторан",  null);
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Обновленный Р1 Mak",  null);
    }
}
