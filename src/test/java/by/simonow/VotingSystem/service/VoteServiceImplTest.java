package by.simonow.VotingSystem.service;

import by.simonow.VotingSystem.VotesTestData;
import by.simonow.VotingSystem.model.Votes;
import by.simonow.VotingSystem.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static by.simonow.VotingSystem.UserTestData.ADMIN_ID;
import static by.simonow.VotingSystem.UserTestData.USER_ID;
import static by.simonow.VotingSystem.VotesTestData.*;
import static java.time.LocalDateTime.of;


public class VoteServiceImplTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void testGet() throws Exception {
        Votes vote = service.get(VOTE1_ID, USER_ID);
        MATCHER.assertEquals(VOTE1, vote);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(VOTE1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE6, VOTE5, VOTE4, VOTE3, VOTE2),service.getAll(USER_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        List<Votes> all = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(VotesTestData.VOTES_USER, all);
    }

    @Test
    public void testUpdate() throws Exception {
        Votes updated = getUpdated();
        service.update(updated,USER_ID);
        MATCHER.assertCollectionEquals(VOTES_USER,service.getAll(USER_ID));

    }

    @Test
    public void testSave() throws Exception {
        Votes created = getCreated();
        service.save(created, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, VOTE6, VOTE5, VOTE4, VOTE3, VOTE2, VOTE1),service.getAll(USER_ID));
    }

    @Test
    public void testGetTodayVote() throws Exception {
        Votes todayVote = service.getTodayVote(of(2017, Month.JANUARY, 12, 0, 0), of(2017, Month.JANUARY, 12, 23, 59), USER_ID);
        MATCHER.assertEquals(VOTE2, todayVote);
    }

    @Test
    public void testNotFoundGetTodayVote() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + ADMIN_ID);
        Votes todayVote = service.getTodayVote(of(2017, Month.JANUARY, 10, 0, 0), of(2017, Month.JANUARY, 10, 23, 59), ADMIN_ID);
    }

    @Test
    public void testNotFoundDelete() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(VOTE1_ID, 1);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(VOTE1_ID, ADMIN_ID);
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + VOTE1_ID);
        service.update(VOTE1, ADMIN_ID);
    }

}