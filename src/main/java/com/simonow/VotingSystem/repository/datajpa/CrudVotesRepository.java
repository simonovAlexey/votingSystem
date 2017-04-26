package com.simonow.VotingSystem.repository.datajpa;

import com.simonow.VotingSystem.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


public interface CrudVotesRepository extends JpaRepository<Votes, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Votes v WHERE v.id=:id AND v.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Votes save(Votes vote);

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT v from Votes v JOIN FETCH v.restaurant WHERE v.user.id=:userId AND v.votedDate BETWEEN :startDate AND :endDate ")
    Votes getVoteByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);


    @Query("SELECT v FROM Votes v WHERE v.user.id=:userId ORDER BY v.votedDate desc ")
    List<Votes> getAllByUser(@Param("userId") int userId);

}
