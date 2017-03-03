package by.simonow.VotingSystem.repository.datajpa;


import by.simonow.VotingSystem.model.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM Meal u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Meal save(Meal meal);

    @Override
    Meal findOne(Integer id);

    List<Meal> getByInmenu(Sort sort);

    @Override
    Page<Meal> findAll(Pageable pageable);

    @Query("SELECT m FROM Meal m JOIN FETCH m.restaurant WHERE m.id = ?1 and m.restaurant.id = ?2")
    Meal getWithRestaurant(int id, int userId);

}
