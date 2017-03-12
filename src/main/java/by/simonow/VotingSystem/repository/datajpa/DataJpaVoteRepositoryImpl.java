package by.simonow.VotingSystem.repository.datajpa;

import by.simonow.VotingSystem.model.Votes;
import by.simonow.VotingSystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVotesRepository crudRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId) !=0;
    }

    @Override
    @Transactional
    public Votes save(Votes vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        return crudRepository.save(vote);
    }

    @Override
    public Votes get(int id, int userId) {
        Votes vote = crudRepository.findOne(id);
        return vote != null && vote.getUser().getId() == userId ? vote : null;
    }

    @Override
    public List<Votes> getAllByUser(int userId) {
        return crudRepository.getAllByUser(userId);
    }

    @Override
    public Votes getTodayVote(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.getTodayVote(startDate, endDate, userId);
    }
}
