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
    Restaurant save(Restaurant restaurant);

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.votes WHERE r.id=?1")
    Restaurant getWithVotes(Integer id);

    @EntityGraph(value = Restaurant.GRAPH_WITH_DISHES)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithDishes(int id);

        @EntityGraph(value = Restaurant.GRAPH_WITH_VOTES)
    @Query("SELECT DISTINCT r FROM Restaurant r")
    List<Restaurant> getAllWithVotes();

}
