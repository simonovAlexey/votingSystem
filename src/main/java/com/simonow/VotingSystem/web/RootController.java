package com.simonow.VotingSystem.web;

import com.simonow.VotingSystem.AuthorizedUser;
import com.simonow.VotingSystem.model.Dish;
import com.simonow.VotingSystem.service.DishService;
import com.simonow.VotingSystem.to.UserTo;
import com.simonow.VotingSystem.util.UserUtil;
import com.simonow.VotingSystem.web.user.AbstractUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RootController extends AbstractUserController {

    @Autowired
    private DishService dishService;

    @GetMapping("/")
    public String root() {
        return "redirect:restaurants";
    }

//    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/restaurants")
    public String restaurants() {
        return "restaurants";
    }

    @GetMapping("/dishes/{id}/{restName}")
    public String dishes(@PathVariable("id") int id, @PathVariable("restName") String restName, ModelMap model) {
        model.addAttribute("restId",id);
        model.addAttribute("restName",restName);
        return "dishes";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status) {
        if (!result.hasErrors()) {
            try {
                super.update(userTo, AuthorizedUser.id());
                AuthorizedUser.get().update(userTo);
                status.setComplete();
                return "redirect:restaurants";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", "exception.users.duplicate_email");
            }
        }
        return "profile";
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                super.create(UserUtil.createNewFromTo(userTo));
                status.setComplete();
                return "redirect:login?message=app.registered&username=" + userTo.getEmail();
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", "exception.users.duplicate_email");
            }
        }
        model.addAttribute("register", true);
        return "profile";
    }

    @GetMapping(value = "/menu={id}")
    public String getMenuById(@PathVariable("id") int id, ModelMap model) {
        List<Dish> menu = dishService.getMenu(id);
        String rName = null;
        if (menu.size()>0) {
            rName = menu.get(0).getRestaurant().getName();
        }
        model.addAttribute("dishes", menu);
        model.addAttribute("restaurant",rName);
        return "fragments/menu";
    }
}
