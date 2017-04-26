package by.simonow.VotingSystem.repository.datajpa;


import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.to.RestaurantTo;
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

//     select RESTAURANT.ID,RESTAURANT.NAME,count(VOTES.RESTAURANT_ID)  from RESTAURANT,VOTES
//         where VOTES.RESTAURANT_ID=RESTAURANT.ID group by  VOTES.RESTAURANT_ID, RESTAURANT.ID
    @Query("SELECT new by.simonow.VotingSystem.to.RestaurantTo(r.id, r.name, count(v)) FROM Votes v LEFT JOIN Restaurant r ON v.restaurant.id=r.id GROUP BY r.id")
    List<RestaurantTo> getAllRestaurantTo();

}
