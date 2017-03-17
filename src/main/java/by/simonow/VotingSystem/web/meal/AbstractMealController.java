package by.simonow.VotingSystem.web.meal;

import by.simonow.VotingSystem.model.Meal;
import by.simonow.VotingSystem.service.MealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.simonow.VotingSystem.util.ValidationUtil.checkIdConsistent;
import static by.simonow.VotingSystem.util.ValidationUtil.checkNew;


public abstract class AbstractMealController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractMealController.class);

    @Autowired
    private MealService service;

    public Meal get(int id, int restId) {
        LOG.info("get meal {} for Restaurant {}", id, restId);
        return service.get(id, restId);
    }

    public void delete(int id, int restId) {
        LOG.info("delete meal {} for Restaurant {}", id, restId);
        service.delete(id, restId);
    }

    public List<Meal> getAll(int restId) {
        LOG.info("getAll for Restaurant id=", restId);
        return service.getAll(restId);
    }

    public void update(Meal meal, int id, int restId) {
        checkIdConsistent(meal, id);
        LOG.info("update {} for Restaurant id=", meal, restId);
        service.update(meal, restId);
    }

    public Meal create(Meal meal, int restId) {
        checkNew(meal);
        LOG.info("create {} for Restaurant id=", meal, restId);
        return service.save(meal, restId);
    }

}