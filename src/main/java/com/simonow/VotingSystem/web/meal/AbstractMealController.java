package com.simonow.VotingSystem.web.meal;

import com.simonow.VotingSystem.model.Dish;
import com.simonow.VotingSystem.service.DishService;
import com.simonow.VotingSystem.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class AbstractMealController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractMealController.class);

    @Autowired
    private DishService service;

    public Dish get(int id, int restId) {
        LOG.info("getWithVotes dish {} for Restaurant {}", id, restId);
        return service.get(id, restId);
    }

    public void delete(int id, int restId) {
        LOG.info("delete dish {} for Restaurant {}", id, restId);
        service.delete(id, restId);
    }

    public List<Dish> getAll(int restId) {
        LOG.info("getAll for Restaurant id=", restId);
        return service.getAll(restId);
    }

    public void update(Dish dish, int id, int restId) {
        ValidationUtil.checkIdConsistent(dish, id);
        LOG.info("update {} for Restaurant id=", dish, restId);
        service.update(dish, restId);
    }

    public Dish create(Dish dish, int restId) {
        ValidationUtil.checkNew(dish);
        LOG.info("create {} for Restaurant id=", dish, restId);
        return service.save(dish, restId);
    }

    public void menuSelect(int id, int restId, boolean inMenu) {
        LOG.info((inMenu ? "in menu " : "not in menu ") + id + " rest: " + restId);
        service.menuSelect(id, restId, inMenu);
    }

}