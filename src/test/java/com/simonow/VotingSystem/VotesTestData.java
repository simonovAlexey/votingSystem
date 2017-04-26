package com.simonow.VotingSystem;


import com.simonow.VotingSystem.matcher.ModelMatcher;
import com.simonow.VotingSystem.model.Votes;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static com.simonow.VotingSystem.model.BaseEntity.START_SEQ;
import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;

public class VotesTestData {

    public static final ModelMatcher<Votes> MATCHER = ModelMatcher.of(Votes.class);


    public static final int VOTE1_ID = START_SEQ + 17;

    public static final Votes VOTE1 = new Votes(VOTE1_ID, of(2017, Month.JANUARY, 11, 10, 0));
    public static final Votes VOTE2 = new Votes(VOTE1_ID + 1, of(2017, Month.JANUARY, 12, 10, 0));
    public static final Votes VOTE3 = new Votes(VOTE1_ID + 2, of(2017, Month.JANUARY, 13, 10, 0));
    public static final Votes VOTE4 = new Votes(VOTE1_ID + 3, of(2017, Month.JANUARY, 14, 10, 0));
    public static final Votes VOTE5 = new Votes(VOTE1_ID + 4, of(2017, Month.JANUARY, 15, 10, 0));
    public static final Votes VOTE6 = new Votes(VOTE1_ID + 5, of(2017, Month.JANUARY, 16, 10, 0));

    public static final Votes VOTE7 = new Votes(VOTE1_ID + 6, of(2017, Month.JANUARY, 11, 10, 0));
    public static final Votes VOTE8 = new Votes(VOTE1_ID + 7, of(2017, Month.JANUARY, 12, 10, 0));
    public static final Votes VOTE9 = new Votes(VOTE1_ID + 8, of(2017, Month.JANUARY, 13, 10, 0));
    public static final Votes VOTE10 = new Votes(VOTE1_ID + 9, of(2017, Month.JANUARY, 14, 10, 0));
    public static final Votes VOTE11 = new Votes(VOTE1_ID + 10, of(2017, Month.JANUARY, 15, 10, 0));
    public static final Votes VOTE12 = new Votes(VOTE1_ID + 11, of(2017, Month.JANUARY, 16, 10, 0));
    public static final Votes VOTE13 = new Votes(VOTE1_ID + 12, of(2017, Month.JANUARY, 17, 10, 0));

    public static final List<Votes> VOTES_R1 = Arrays.asList(VOTE13,VOTE4);
    public static final List<Votes> VOTES_R2 = Arrays.asList(VOTE12,VOTE10,VOTE9,VOTE7,VOTE3,VOTE2,VOTE1);
    public static final List<Votes> VOTES_USER = Arrays.asList(VOTE6,VOTE5,VOTE4,VOTE3,VOTE2,VOTE1);
    public static final List<Votes> VOTES_R3 = Arrays.asList(VOTE11,VOTE8,VOTE6,VOTE5);

    public static Votes getCreated() {
        Votes created = new Votes(null, now());
        created.setRestaurant(RestaurantTestData.RESTAURANT1);
//        created.setUser(UserTestData.USER);
        return created;
    }

    public static Votes getUpdated() {
        Votes updated = VOTE1;
        updated.setId(VotesTestData.VOTE1_ID);
        updated.setRestaurant(RestaurantTestData.RESTAURANT1);
        updated.setUser(UserTestData.USER);
        return updated;
    }


}
