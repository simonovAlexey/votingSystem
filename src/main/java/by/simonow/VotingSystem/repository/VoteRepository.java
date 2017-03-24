package by.simonow.VotingSystem.repository;

import by.simonow.VotingSystem.model.Votes;

import java.time.LocalDateTime;
import java.util.List;


public interface VoteRepository {

    // false if vote do not belong to userId
    boolean delete(int id, int userId);

    // null if updated vote do not belong to userId
    Votes save(Votes vote, int userId);

    // null if vote do not belong to userId
    Votes get(int id, int userId);

    List<Votes> getAllByUser(int userId);

    Votes getVoteByDate(LocalDateTime startDate, LocalDateTime endDate, int userId);

    Votes getTodayVote(int userId);

}
