package by.simonow.VotingSystem.service;


import by.simonow.VotingSystem.model.Votes;
import by.simonow.VotingSystem.util.exception.NotFoundException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteService {

    Votes get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    List<Votes> getAll(int userId);

    Votes update(Votes votes, int userId) throws NotFoundException;

    Votes save(Votes votes, int userId);

    Votes getTodayVote(LocalDateTime startDate, LocalDateTime endDate, int userId);

    Votes vote(Votes votes, int userId) throws DateTimeException,IllegalArgumentException,NotFoundException;

}
