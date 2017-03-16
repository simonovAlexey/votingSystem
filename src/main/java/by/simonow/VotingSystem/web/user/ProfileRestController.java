package by.simonow.VotingSystem.web.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import by.simonow.VotingSystem.AuthorizedUser;
import by.simonow.VotingSystem.model.User;
import by.simonow.VotingSystem.to.UserTo;

import javax.validation.Valid;


@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.id());
    }

    @DeleteMapping
    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody UserTo userTo) {
        super.update(userTo, AuthorizedUser.id());
    }

    @GetMapping(value = "/text")
    public String testUTF() {
        return "Русский текст";
    }
}