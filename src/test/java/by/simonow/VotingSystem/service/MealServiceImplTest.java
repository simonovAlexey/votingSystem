package by.simonow.VotingSystem.service;

import by.simonow.VotingSystem.RestaurantTestData;
import by.simonow.VotingSystem.model.Meal;
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


public class MealServiceImplTest extends AbstractServiceTest {

    @Autowired
    private MealService service;

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
        Meal actual = service.get(MEAL1_ID,RESTAURANT1_ID);
        MATCHER.assertEquals(MEAL1, actual);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL1_ID,RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL2,MEAL3),service.getAll(RESTAURANT1_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Meal> meals = service.getAll(RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(MEALS_R1, meals);
    }

    @Test
    public void testGetMenu() throws Exception {
        Collection<Meal> meals = service.getMenu(RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(MENU1, meals);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        MATCHER.assertEquals(updated, service.get(MEAL1_ID, RESTAURANT1_ID));
    }

    @Test
    public void testSave() throws Exception {
        Meal created = getCreated();
        service.save(created,RESTAURANT1_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL1,MEAL2,MEAL3,created),service.getAll(RESTAURANT1_ID));
    }

    @Test
    public void testGetWithRestaurant() throws Exception {
        Meal withRestaurant = service.getWithRestaurant(MEAL1_ID, RESTAURANT1_ID);
        Restaurant restaurant = withRestaurant.getRestaurant();
        RestaurantTestData.MATCHER.assertEquals(RESTAURANT1,restaurant);
        MATCHER.assertEquals(MEAL1,withRestaurant);
    }

    @Test
    public void testNotFoundDelete() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(MEAL1_ID, 1);
    }

    @Test
    public void testGetNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(MEAL1_ID, RESTAURANT2_ID);
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + MEAL1_ID);
        service.update(MEAL1, RESTAURANT2_ID);
    }
    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.save(new Meal(null, true,"Созданная еда", 0),RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new Meal(null, true," ", 0),RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.save(new Meal(null, true, null, 300), RESTAURANT1_ID), ConstraintViolationException.class);
    }
}