package com.simonow.VotingSystem;


import com.simonow.VotingSystem.matcher.ModelMatcher;
import com.simonow.VotingSystem.model.Dish;
import com.simonow.VotingSystem.model.BaseEntity;

import java.util.Arrays;
import java.util.List;


public class MealTestData {

    public static final ModelMatcher<Dish> MATCHER = ModelMatcher.of(Dish.class);

    public static final int DISH1_ID = BaseEntity.START_SEQ + 5;

    public static final Dish DISH_1 = new Dish(DISH1_ID, true, "Гамбургер", 200);
    public static final Dish DISH_2 = new Dish(DISH1_ID + 1, false, "Чизбургер", 1050);
    public static final Dish DISH_3 = new Dish(DISH1_ID + 2, true, "Картошка", 945);
    public static final Dish DISH_4 = new Dish(DISH1_ID + 3, true, "Стейк", 200);
    public static final Dish DISH_5 = new Dish(DISH1_ID + 4, true, "Паста", 250);
    public static final Dish DISH_6 = new Dish(DISH1_ID + 5, false, "Дичь", 250);
    public static final Dish DISH_7 = new Dish(DISH1_ID + 6, true, "Завтрак", 190);
    public static final Dish DISH_8 = new Dish(DISH1_ID + 7, true, "Обед", 90);
    public static final Dish DISH_9 = new Dish(DISH1_ID + 8, false, "Комплекс", 290);
    public static final Dish DISH_10 = new Dish(DISH1_ID + 9, true, "Компот", 390);
    public static final Dish DISH_11 = new Dish(DISH1_ID + 10, true, "Ужин", 490);
    public static final Dish DISH_12 = new Dish(DISH1_ID + 11, true, "Кефир", 590);

    public static final List<Dish> MENU1 = Arrays.asList(DISH_1, DISH_3);
    public static final List<Dish> MENU2 = Arrays.asList(DISH_4, DISH_5);
    public static final List<Dish> MENU3 = Arrays.asList(DISH_7, DISH_8, DISH_10, DISH_11, DISH_12);
    public static final List<Dish> DISHES_R_1 = Arrays.asList(DISH_1, DISH_2, DISH_3);
    public static final List<Dish> DISHES_R_2 = Arrays.asList(DISH_4, DISH_5, DISH_6);
    public static final List<Dish> DISHES_R_3 = Arrays.asList(DISH_7, DISH_8, DISH_9, DISH_10, DISH_11, DISH_12);

    public static Dish getCreated() {
        return new Dish(null, false, "Созданный ужин", 300);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, DISH_1.isInMenu(), "Обновленный завтрак", 2000);
    }
}
