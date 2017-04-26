package com.simonow.VotingSystem.service;

import com.simonow.VotingSystem.RestaurantTestData;
import com.simonow.VotingSystem.UserTestData;
import com.simonow.VotingSystem.VoteTime;
import com.simonow.VotingSystem.VotesTestData;
import com.simonow.VotingSystem.model.Votes;
import com.simonow.VotingSystem.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DateTimeException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;


public class VoteServiceImplTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void testGet() throws Exception {
        Votes vote = service.get(VotesTestData.VOTE1_ID, UserTestData.USER_ID);
        VotesTestData.MATCHER.assertEquals(VotesTestData.VOTE1, vote);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(VotesTestData.VOTE1_ID, UserTestData.USER_ID);
        VotesTestData.MATCHER.assertCollectionEquals(Arrays.asList(VotesTestData.VOTE6, VotesTestData.VOTE5, VotesTestData.VOTE4, VotesTestData.VOTE3, VotesTestData.VOTE2),service.getAll(UserTestData.USER_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        List<Votes> all = service.getAll(UserTestData.USER_ID);
        VotesTestData.MATCHER.assertCollectionEquals(VotesTestData.VOTES_USER, all);
    }

    @Test
    public void testUpdate() throws Exception {
        Votes updated = VotesTestData.getUpdated();
        service.update(updated, UserTestData.USER_ID);
        VotesTestData.MATCHER.assertCollectionEquals(VotesTestData.VOTES_USER,service.getAll(UserTestData.USER_ID));

    }

    @Test
    public void testSave() throws Exception {
        Votes created = VotesTestData.getCreated();
        service.save(created, UserTestData.USER_ID);
        VotesTestData.MATCHER.assertCollectionEquals(Arrays.asList(created, VotesTestData.VOTE6, VotesTestData.VOTE5, VotesTestData.VOTE4, VotesTestData.VOTE3, VotesTestData.VOTE2, VotesTestData.VOTE1),service.getAll(UserTestData.USER_ID));
    }

    @Test
    public void testGetTodayVote() throws Exception {
        Votes todayVote = service.getVoteByDate(of(2017, Month.JANUARY, 12, 0, 0), of(2017, Month.JANUARY, 12, 23, 59), UserTestData.USER_ID);
        VotesTestData.MATCHER.assertEquals(VotesTestData.VOTE2, todayVote);
    }

    @Test
    public void testNotFoundGetTodayVote() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + UserTestData.ADMIN_ID);
        Votes todayVote = service.getVoteByDate(of(2017, Month.JANUARY, 10, 0, 0), of(2017, Month.JANUARY, 10, 23, 59), UserTestData.ADMIN_ID);
    }

    @Test
    public void testNotFoundDelete() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(VotesTestData.VOTE1_ID, 1);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(VotesTestData.VOTE1_ID, UserTestData.ADMIN_ID);
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + VotesTestData.VOTE1_ID);
        service.update(VotesTestData.VOTE1, UserTestData.ADMIN_ID);
    }

    @Test
    public void testVote() throws Exception {
        Votes updated = VotesTestData.getCreated();
        Votes vote = service.vote(RestaurantTestData.RESTAURANT1, UserTestData.USER);
        List<Votes> list = new ArrayList<>(VotesTestData.VOTES_USER);
                list.add(0,vote);
        VotesTestData.MATCHER.assertCollectionEquals(list,service.getAll(UserTestData.USER_ID));


        if (now().toLocalTime().isAfter(VoteTime.MAX_VOTE_TIME)){
            thrown.expect(DateTimeException.class);
        }
        service.vote(RestaurantTestData.RESTAURANT2, UserTestData.USER);
        VotesTestData.MATCHER.assertCollectionEquals(list,service.getAll(UserTestData.USER_ID));
    }

}