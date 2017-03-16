package by.simonow.VotingSystem.service;


import by.simonow.VotingSystem.VoteTime;
import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.model.User;
import by.simonow.VotingSystem.model.Votes;
import by.simonow.VotingSystem.repository.VoteRepository;
import by.simonow.VotingSystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static by.simonow.VotingSystem.util.ValidationUtil.checkNotFoundWithId;
import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;

@Service("voteService")
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

    @Override
    public Votes get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public List<Votes> getAll(int userId) {
        return repository.getAllByUser(userId);
    }

    @Override
    public Votes update(Votes votes, int userId) throws NotFoundException {
        Assert.notNull(votes, "vote must not be null");
        return checkNotFoundWithId(repository.save(votes, userId), votes.getId());
    }

    @Override
    public Votes save(Votes votes, int userId) {
        Assert.notNull(votes, "vote must not be null");
        return repository.save(votes, userId);
    }

    @Override
    @Transactional
    public Votes vote(Restaurant rest, User user) throws DateTimeException, IllegalArgumentException, NotFoundException {
        Assert.notNull(rest, "rest must not be null");
        Assert.notNull(user, "user must not be null");
        LocalDateTime dateTime = now();
        LocalDateTime startDate = of(dateTime.toLocalDate(), LocalTime.MIN);
        LocalDateTime endDate = of(dateTime.toLocalDate(), LocalTime.MAX);
        Votes voteOld = null;
        Integer userId = user.getId();
        try {
            voteOld = getTodayVote(startDate, endDate, userId);
        } catch (NotFoundException e) {
            return repository.save(new Votes(dateTime, rest, user), userId);
        }

        if (dateTime.toLocalTime().isBefore(VoteTime.MAX_VOTE_TIME)) {
            voteOld.setRestaurant(rest);
            voteOld.setVotedDate(dateTime);
            return checkNotFoundWithId(repository.save(voteOld, userId), voteOld.getId());
        } else throw new DateTimeException(" It's too late, vote can be changed up to " + VoteTime.MAX_VOTE_TIME);
    }

    @Override
    public Votes getTodayVote(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate must not be null");
        return checkNotFoundWithId(repository.getTodayVote(startDate, endDate, userId), userId);
    }
}
