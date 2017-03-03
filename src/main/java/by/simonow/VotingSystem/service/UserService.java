package by.simonow.VotingSystem.service;


import by.simonow.VotingSystem.model.User;
import by.simonow.VotingSystem.to.UserTo;
import by.simonow.VotingSystem.util.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(UserTo user);

    List<User> getAll();

    Page<User> getAllByPage(Pageable pageable);

    void update(User user);

    void enable(int id, boolean enable);

    void vote(int userId,int restaurantId);

}
