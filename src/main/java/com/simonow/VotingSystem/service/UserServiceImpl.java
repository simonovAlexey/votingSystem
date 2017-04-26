package com.simonow.VotingSystem.service;

import com.simonow.VotingSystem.AuthorizedUser;
import com.simonow.VotingSystem.model.User;
import com.simonow.VotingSystem.model.Votes;
import com.simonow.VotingSystem.repository.UserRepository;
import com.simonow.VotingSystem.repository.VoteRepository;
import com.simonow.VotingSystem.to.UserTo;
import com.simonow.VotingSystem.util.UserUtil;
import com.simonow.VotingSystem.util.exception.NotFoundException;
import com.simonow.VotingSystem.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.simonow.VotingSystem.util.ValidationUtil.checkNotFound;
import static com.simonow.VotingSystem.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(UserUtil.prepareToSave(user));
    }

    @Override
    public void delete(int id) {
        ValidationUtil.checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return ValidationUtil.checkNotFoundWithId(userRepository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return ValidationUtil.checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        userRepository.save(UserUtil.prepareToSave(user));
    }

    @Transactional
    @Override
    public void update(UserTo userTo) {
        User user = UserUtil.updateFromTo(get(userTo.getId()), userTo);
        userRepository.save(UserUtil.prepareToSave(user));
    }

    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userRepository.getByEmail(email.toLowerCase());
        if (u == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }

        Votes todayVote = null;
        try {
            todayVote = voteRepository.getTodayVote(u.getId());
        } catch (Exception e) {
            //user not voted today
        }
        return new AuthorizedUser(u, todayVote);
    }

}
