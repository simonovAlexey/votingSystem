package by.simonow.VotingSystem.repository.datajpa;


import by.simonow.VotingSystem.model.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.restaurant.id=:restId")
    int delete(@Param("id") int id,@Param("restId") int restId);

    @Override
    @Transactional
    Meal save(Meal meal);

    @Override
    Meal findOne(Integer id);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restId and m.inMenu=true ORDER BY m.description")
    List<Meal> getMenu(@Param("restId") int restId);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restId ORDER BY m.inMenu")
    List<Meal> getAllM(@Param("restId") int restId);

    @Override
    Page<Meal> findAll(Pageable pageable);

    @Query("SELECT m FROM Meal m JOIN FETCH m.restaurant WHERE m.id = ?1 and m.restaurant.id = ?2")
    Meal getWithRestaurant(int id, int userId);

}
