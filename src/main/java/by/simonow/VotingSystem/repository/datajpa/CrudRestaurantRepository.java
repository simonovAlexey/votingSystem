package by.simonow.VotingSystem.repository.datajpa;


import by.simonow.VotingSystem.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Restaurant save(Restaurant meal);

    @Override
    Restaurant findOne(Integer id);

    List<Restaurant> getAllwithVotesCount();
    List<Restaurant> findAll();

    @EntityGraph(value = Restaurant.GRAPH_WITH_ALL)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithMealsAndWithVotes(int id);


    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.meals WHERE r.id=?1")
    Restaurant getWithVotes(int id);

}
