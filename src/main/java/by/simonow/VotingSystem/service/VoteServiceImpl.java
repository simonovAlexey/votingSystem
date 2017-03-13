package by.simonow.VotingSystem.service;


import by.simonow.VotingSystem.model.Votes;
import by.simonow.VotingSystem.repository.VoteRepository;
import by.simonow.VotingSystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

import static by.simonow.VotingSystem.util.ValidationUtil.checkNotFoundWithId;

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
    public Votes getTodayVote(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Assert.notNull(startDate, "startDate must not be null");
        Assert.notNull(endDate, "endDate must not be null");
        return checkNotFoundWithId(repository.getTodayVote(startDate, endDate, userId), userId);
    }
}
