package com.simonow.VotingSystem.web.vote;

import com.simonow.VotingSystem.model.Votes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/rest/profile/votes/u")
public class VoteRestController extends AbstractVoteController {

    @Override
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Votes> getAll(@PathVariable("userId") int userId) {
        return super.getAll(userId);
    }

    @Override
    @GetMapping(value = "/{userId}/{id}")
    public Votes get(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        return super.get(id, userId);
    }

    @Override
    @DeleteMapping(value = "/{userId}/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        super.delete(id, userId);
    }

    @PostMapping
    public void updateOrCreate(@Valid Votes vote, @RequestParam("userId") int userId) {
        if (vote.isNew()) {
            super.create(vote, userId);
        } else {
            super.update(vote, vote.getId(), userId);
        }
    }
}
