package by.simonow.VotingSystem;


import by.simonow.VotingSystem.matcher.ModelMatcher;
import by.simonow.VotingSystem.model.Meal;

import java.util.Arrays;
import java.util.List;

import static by.simonow.VotingSystem.model.BaseEntity.START_SEQ;


public class MealTestData {

    public static final ModelMatcher<Meal> MATCHER = ModelMatcher.of(Meal.class);

    public static final int MEAL1_ID = START_SEQ + 5;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, true, "Р1 Завтрак в меню", 200);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, false, "Р1 Обед не в меню", 1050);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, true, "Р1 Ужин в меню", 945);
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, true, "Р2 Завтрак в меню", 200);
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, true, "Р2 Обед в меню", 250);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, false, "Р2 Ужин не в меню", 250);
    public static final Meal MEAL7 = new Meal(MEAL1_ID + 6, true, "Р3 Завтрак-б в меню", 90);
    public static final Meal MEAL8 = new Meal(MEAL1_ID + 7, true, "Р3 Обед-б в меню", 90);
    public static final Meal MEAL9 = new Meal(MEAL1_ID + 8, false, "Р3 Ужин-б не в меню", 90);

    public static final List<Meal> MENU1 = Arrays.asList(MEAL1,MEAL3);
    public static final List<Meal> MEALS_R1 = Arrays.asList(MEAL1,MEAL3,MEAL2);
    public static final List<Meal> MEALS_R2 = Arrays.asList(MEAL4,MEAL5,MEAL6);
    public static final List<Meal> MEALS_R3 = Arrays.asList(MEAL7,MEAL8,MEAL9);

    public static Meal getCreated() {
        return new Meal(null, false, "Созданный ужин", 300);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, MEAL1.isInMenu(), "Обновленный завтрак", 2000);
    }
}
