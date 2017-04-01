package by.simonow.VotingSystem;


import by.simonow.VotingSystem.matcher.ModelMatcher;
import by.simonow.VotingSystem.model.Meal;

import java.util.Arrays;
import java.util.List;

import static by.simonow.VotingSystem.model.BaseEntity.START_SEQ;


public class MealTestData {

    public static final ModelMatcher<Meal> MATCHER = ModelMatcher.of(Meal.class);

    public static final int MEAL1_ID = START_SEQ + 5;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, true, "Гамбургер", 200);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, false, "Чизбургер", 1050);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, true, "Картошка", 945);
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, true, "Стейк", 200);
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, true, "Паста", 250);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, false, "Дичь", 250);
    public static final Meal MEAL7 = new Meal(MEAL1_ID + 6, true, "Завтрак", 190);
    public static final Meal MEAL8 = new Meal(MEAL1_ID + 7, true, "Обед", 90);
    public static final Meal MEAL9 = new Meal(MEAL1_ID + 8, false, "Комплекс", 290);
    public static final Meal MEAL10 = new Meal(MEAL1_ID + 9, true, "Компот", 390);
    public static final Meal MEAL11 = new Meal(MEAL1_ID + 10, true, "Ужин", 490);
    public static final Meal MEAL12 = new Meal(MEAL1_ID + 11, true, "Кефир", 590);

    public static final List<Meal> MENU1 = Arrays.asList(MEAL1,MEAL3);
    public static final List<Meal> MENU2 = Arrays.asList(MEAL4,MEAL5);
    public static final List<Meal> MENU3 = Arrays.asList(MEAL7,MEAL8,MEAL10,MEAL11,MEAL12);
    public static final List<Meal> MEALS_R1 = Arrays.asList(MEAL1,MEAL2,MEAL3);
    public static final List<Meal> MEALS_R2 = Arrays.asList(MEAL4,MEAL5,MEAL6);
    public static final List<Meal> MEALS_R3 = Arrays.asList(MEAL7,MEAL8,MEAL9,MEAL10,MEAL11,MEAL12);

    public static Meal getCreated() {
        return new Meal(null, false, "Созданный ужин", 300);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, MEAL1.isInMenu(), "Обновленный завтрак", 2000);
    }
}
