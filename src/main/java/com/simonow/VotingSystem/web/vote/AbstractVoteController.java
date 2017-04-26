package com.simonow.VotingSystem.web.vote;

import com.simonow.VotingSystem.model.Votes;
import com.simonow.VotingSystem.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.simonow.VotingSystem.util.ValidationUtil.checkIdConsistent;
import static com.simonow.VotingSystem.util.ValidationUtil.checkNew;


public abstract class AbstractVoteController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractVoteController.class);

    @Autowired
    private VoteService service;

    public Votes get(int id, int userId) {
        LOG.info("getVote {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id, int userId) {
        LOG.info("delete vote {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<Votes> getAll(int userId) {
        LOG.info("getAll  votes for User id=", userId);
        return service.getAll(userId);
    }

    public void update(Votes vote, int id, int userId) {
        checkIdConsistent(vote, id);
        LOG.info("update {} for User id=", vote, userId);
        service.update(vote, userId);
    }

    public Votes create(Votes vote, int userId) {
        checkNew(vote);
        LOG.info("create {} for User id=", vote, userId);
        return service.save(vote, userId);
    }

}