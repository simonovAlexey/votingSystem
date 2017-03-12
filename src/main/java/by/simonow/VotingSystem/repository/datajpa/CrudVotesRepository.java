package by.simonow.VotingSystem.repository.datajpa;

import by.simonow.VotingSystem.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Алексей on 12.03.2017.
 */

public interface CrudVotesRepository extends JpaRepository<Votes, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Votes save(Votes vote);

    @Override
    Votes findOne(Integer id);

//    @Query("SELECT v FROM Votes v WHERE v.user.id=:userId ORDER BY v.votedDate desc ")
    Votes getByUserAndVotedDate(int userId, LocalDateTime votedDate);


    @Query("SELECT v FROM Votes v WHERE v.user.id=:userId ORDER BY v.votedDate desc ")
    List<Votes> getAllByUser(@Param("userId") int userId);



    @Override
    List<Votes> findAll();

}
