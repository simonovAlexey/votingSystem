package com.simonow.VotingSystem.service;


import com.simonow.VotingSystem.model.Restaurant;
import com.simonow.VotingSystem.model.User;
import com.simonow.VotingSystem.model.Votes;
import com.simonow.VotingSystem.util.exception.NotFoundException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteService {

    Votes get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    List<Votes> getAll(int userId);

    Votes update(Votes votes, int userId) throws NotFoundException;

    Votes save(Votes votes, int userId);

    Votes getVoteByDate(LocalDateTime startDate, LocalDateTime endDate, int userId);

    Votes vote(Restaurant rest, User user) throws DateTimeException, IllegalArgumentException, NotFoundException;

}
