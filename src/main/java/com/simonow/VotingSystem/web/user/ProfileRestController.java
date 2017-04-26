package com.simonow.VotingSystem.web.user;

import com.simonow.VotingSystem.AuthorizedUser;
import com.simonow.VotingSystem.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.simonow.VotingSystem.to.UserTo;

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