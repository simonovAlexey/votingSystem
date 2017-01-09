package repository;

import model.Meal;

import java.util.Collection;

/**
 * Created by Алексей on 09.01.2017.
 */
public interface MealRepository {
     // null if updated meal do not belong to userId
    Meal save(Meal meal, int restaurantId);

    // false if meal do not belong to userId
    boolean delete(int id, int restaurantId);

    // null if meal do not belong to userId
    Meal get(int id, int restaurantId);

    // ORDERED description
    Collection<Meal> getAll(int restaurantId);

    // ORDERED description
    Collection<Meal> getInmenu(int restaurantId);
}
