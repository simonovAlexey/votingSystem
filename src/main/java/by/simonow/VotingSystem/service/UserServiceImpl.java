package by.simonow.VotingSystem.service;

import by.simonow.VotingSystem.AuthorizedUser;
import by.simonow.VotingSystem.model.User;
import by.simonow.VotingSystem.model.Votes;
import by.simonow.VotingSystem.repository.UserRepository;
import by.simonow.VotingSystem.repository.VoteRepository;
import by.simonow.VotingSystem.to.UserTo;
import by.simonow.VotingSystem.util.UserUtil;
import by.simonow.VotingSystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static by.simonow.VotingSystem.util.UserUtil.prepareToSave;
import static by.simonow.VotingSystem.util.ValidationUtil.checkNotFound;
import static by.simonow.VotingSystem.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(prepareToSave(user));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        userRepository.save(prepareToSave(user));
    }

    @Transactional
    @Override
    public void update(UserTo userTo) {
        User user = UserUtil.updateFromTo(get(userTo.getId()), userTo);
        userRepository.save(prepareToSave(user));
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
