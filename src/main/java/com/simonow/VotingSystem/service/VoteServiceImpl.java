package com.simonow.VotingSystem.service;


import com.simonow.VotingSystem.VoteTime;
import com.simonow.VotingSystem.model.Restaurant;
import com.simonow.VotingSystem.model.User;
import com.simonow.VotingSystem.model.Votes;
import com.simonow.VotingSystem.repository.VoteRepository;
import com.simonow.VotingSystem.util.exception.NotFoundException;
import com.simonow.VotingSystem.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

import static com.simonow.VotingSystem.util.ValidationUtil.checkNotFoundWithId;
import static java.time.LocalDateTime.now;

@Service("voteService")
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

    @Override
    public Votes get(int id, int userId) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        ValidationUtil.checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public List<Votes> getAll(int userId) {
        return repository.getAllByUser(userId);
    }

    @Override
    public Votes update(Votes votes, int userId) throws NotFoundException {
        Assert.notNull(votes, "vote must not be null");
        return ValidationUtil.checkNotFoundWithId(repository.save(votes, userId), votes.getId());
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
        Integer userId = user.getId();
        Votes voteOld = repository.getTodayVote(userId);
        if (voteOld == null) return repository.save(new Votes(dateTime, rest, user), userId);
        if (voteOld.getRestaurant().getId()==rest.getId()) return voteOld;

        if (dateTime.toLocalTime().isBefore(VoteTime.MAX_VOTE_TIME)) {
            voteOld.setRestaurant(rest);
            voteOld.setVotedDate(dateTime);
            return ValidationUtil.checkNotFoundWithId(repository.save(voteOld, userId), voteOld.getId());
        } else throw new DateTimeException(" It's too late, vote can be changed up to " + VoteTime.MAX_VOTE_TIME);
    }

    @Override
    public Votes getVoteByDate(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate must not be null");
        return ValidationUtil.checkNotFoundWithId(repository.getVoteByDate(startDate, endDate, userId), userId);
    }
}
