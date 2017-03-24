package by.simonow.VotingSystem.repository.datajpa;

import by.simonow.VotingSystem.model.Votes;
import by.simonow.VotingSystem.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;


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
    public Votes getVoteByDate(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.getVoteByDate(startDate, endDate, userId);
    }

    @Override
    public Votes getTodayVote(int userId) {
        LocalDateTime dateTime = now();
        LocalDateTime startDate = of(dateTime.toLocalDate(), LocalTime.MIN);
        LocalDateTime endDate = of(dateTime.toLocalDate(), LocalTime.MAX);
        return getVoteByDate(startDate,endDate,userId);
    }
}
