package by.simonow.VotingSystem.repository.datajpa;


import by.simonow.VotingSystem.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish m WHERE m.id=:id AND m.restaurant.id=:restId")
    int delete(@Param("id") int id,@Param("restId") int restId);

    @Override
    @Transactional
    Dish save(Dish dish);

    @Query("SELECT m FROM Dish m JOIN FETCH m.restaurant WHERE m.restaurant.id=:restId and m.inMenu=true")
    List<Dish> getMenu(@Param("restId") int restId);

    @Query("SELECT m FROM Dish m JOIN FETCH m.restaurant WHERE m.restaurant.id=:restId")
    List<Dish> getAllDishes(@Param("restId") int restId);


    @Query("SELECT m FROM Dish m JOIN FETCH m.restaurant WHERE m.id = ?1 and m.restaurant.id = ?2")
    Dish getWithRestaurant(int id, int userId);

}
