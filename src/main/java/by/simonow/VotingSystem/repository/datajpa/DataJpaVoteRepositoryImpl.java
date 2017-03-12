package by.simonow.VotingSystem.repository.datajpa;

import by.simonow.VotingSystem.model.Votes;
import by.simonow.VotingSystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVotesRepository crudRepository;

    @Override
    public Votes getTodayVote(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.getTodayVote(startDate,endDate,userId);
    }
}
