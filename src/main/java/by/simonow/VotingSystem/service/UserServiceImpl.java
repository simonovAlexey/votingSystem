package by.simonow.VotingSystem.service;

import by.simonow.VotingSystem.model.Restaurant;
import by.simonow.VotingSystem.model.User;
import by.simonow.VotingSystem.repository.RestaurantRepository;
import by.simonow.VotingSystem.repository.UserRepository;
import by.simonow.VotingSystem.to.UserTo;
import by.simonow.VotingSystem.util.UserUtil;
import by.simonow.VotingSystem.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static by.simonow.VotingSystem.util.ValidationUtil.checkNotFound;
import static by.simonow.VotingSystem.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
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

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Cacheable("users")
    @Override
    public Page<User> getAllByPage(Pageable pageable){
        return userRepository.findAllByPage(pageable);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        userRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    @Override
    public void update(UserTo userTo) {
        User user = get(userTo.getId());
        userRepository.save(UserUtil.updateFromTo(user, userTo));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void vote(int userId, int restaurantId) {
        User user = get(userId);
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.get(restaurantId), restaurantId);
        //TODO user vote
        /*user.setVotedRestaurant(restaurant);
        user.setVotedDate(LocalDateTime.now());
        restaurant.setVotes(restaurant.getVotes()+1);

        userRepository.save(user);
        restaurantRepository.save(restaurant);*/

    }

}
