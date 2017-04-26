package com.simonow.VotingSystem.service;

import com.simonow.VotingSystem.RestaurantTestData;
import com.simonow.VotingSystem.model.Dish;
import com.simonow.VotingSystem.model.Restaurant;
import com.simonow.VotingSystem.util.JpaUtil;
import com.simonow.VotingSystem.util.exception.NotFoundException;
import com.simonow.VotingSystem.MealTestData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;

import static com.simonow.VotingSystem.RestaurantTestData.RESTAURANT1;
import static com.simonow.VotingSystem.RestaurantTestData.RESTAURANT1_ID;
import static com.simonow.VotingSystem.RestaurantTestData.RESTAURANT2_ID;


public class DishServiceImplTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JpaUtil jpaUtil;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void testGet() throws Exception {
        Dish actual = service.get(MealTestData.DISH1_ID,RESTAURANT1_ID);
        MealTestData.MATCHER.assertEquals(MealTestData.DISH_1, actual);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MealTestData.DISH1_ID,RESTAURANT1_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MealTestData.DISH_2, MealTestData.DISH_3),service.getAll(RESTAURANT1_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Dish> dishes = service.getAll(RESTAURANT1_ID);
        MealTestData.MATCHER.assertCollectionEquals(MealTestData.DISHES_R_1, dishes);
    }

    @Test
    public void testGetMenu() throws Exception {
        Collection<Dish> dishes = service.getMenu(RESTAURANT1_ID);
        MealTestData.MATCHER.assertCollectionEquals(MealTestData.MENU1, dishes);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = MealTestData.getUpdated();
        service.update(updated, RESTAURANT1_ID);
        MealTestData.MATCHER.assertEquals(updated, service.get(MealTestData.DISH1_ID, RESTAURANT1_ID));
    }

    @Test
    public void testSave() throws Exception {
        Dish created = MealTestData.getCreated();
        service.save(created,RESTAURANT1_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MealTestData.DISH_1, MealTestData.DISH_2, MealTestData.DISH_3,created),service.getAll(RESTAURANT1_ID));
    }

    @Test
    public void testGetWithRestaurant() throws Exception {
        Dish withRestaurant = service.getWithRestaurant(MealTestData.DISH1_ID, RESTAURANT1_ID);
        Restaurant restaurant = withRestaurant.getRestaurant();
        RestaurantTestData.MATCHER.assertEquals(RESTAURANT1,restaurant);
        MealTestData.MATCHER.assertEquals(MealTestData.DISH_1,withRestaurant);
    }

    @Test
    public void testNotFoundDelete() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(MealTestData.DISH1_ID, 1);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(MealTestData.DISH1_ID, RESTAURANT2_ID);
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + MealTestData.DISH1_ID);
        service.update(MealTestData.DISH_1, RESTAURANT2_ID);
    }
    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.save(new Dish(null, true,"Созданная еда", 0),RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new Dish(null, true," ", 0),RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new Dish(null, true, null, 300), RESTAURANT1_ID), ConstraintViolationException.class);
    }
}