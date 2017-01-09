package repository;


import model.Meal;

import java.util.Collection;


public interface MealRepository {
    // null if updated meal do not belong to userId
    Meal save(Meal meal, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Meal get(int id, int userId);

    // ORDERED description
    Collection<Meal> getAll(int userId);

    // ORDERED description
    Collection<Meal> getInmenu(int userId);
}
