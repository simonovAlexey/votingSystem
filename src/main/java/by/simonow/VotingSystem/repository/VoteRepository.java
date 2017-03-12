package by.simonow.VotingSystem.repository;

import by.simonow.VotingSystem.model.Votes;

import java.time.LocalDateTime;


public interface VoteRepository {

    Votes getTodayVote(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
