package by.simonow.VotingSystem.service;

import by.simonow.VotingSystem.RestaurantTestData;
import by.simonow.VotingSystem.model.Dish;
import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.util.JpaUtil;
import by.simonow.VotingSystem.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collection;

import static by.simonow.VotingSystem.MealTestData.*;
import static by.simonow.VotingSystem.RestaurantTestData.RESTAURANT1;
import static by.simonow.VotingSystem.RestaurantTestData.RESTAURANT1_ID;
import static by.simonow.VotingSystem.RestaurantTestData.RESTAURANT2_ID;


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
        Dish actual = service.get(DISH1_ID,RESTAURANT1_ID);
        MATCHER.assertEquals(DISH_1, actual);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(DISH1_ID,RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_2, DISH_3),service.getAll(RESTAURANT1_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Dish> dishes = service.getAll(RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(DISHES_R_1, dishes);
    }

    @Test
    public void testGetMenu() throws Exception {
        Collection<Dish> dishes = service.getMenu(RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(MENU1, dishes);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        MATCHER.assertEquals(updated, service.get(DISH1_ID, RESTAURANT1_ID));
    }

    @Test
    public void testSave() throws Exception {
        Dish created = getCreated();
        service.save(created,RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_1, DISH_2, DISH_3,created),service.getAll(RESTAURANT1_ID));
    }

    @Test
    public void testGetWithRestaurant() throws Exception {
        Dish withRestaurant = service.getWithRestaurant(DISH1_ID, RESTAURANT1_ID);
        Restaurant restaurant = withRestaurant.getRestaurant();
        RestaurantTestData.MATCHER.assertEquals(RESTAURANT1,restaurant);
        MATCHER.assertEquals(DISH_1,withRestaurant);
    }

    @Test
    public void testNotFoundDelete() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(DISH1_ID, 1);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(DISH1_ID, RESTAURANT2_ID);
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + DISH1_ID);
        service.update(DISH_1, RESTAURANT2_ID);
    }
    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.save(new Dish(null, true,"Созданная еда", 0),RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new Dish(null, true," ", 0),RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new Dish(null, true, null, 300), RESTAURANT1_ID), ConstraintViolationException.class);
    }
}