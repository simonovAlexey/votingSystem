package com.simonow.VotingSystem.service;


import com.simonow.VotingSystem.model.User;
import com.simonow.VotingSystem.to.UserTo;
import com.simonow.VotingSystem.util.exception.NotFoundException;

import java.util.List;


public interface UserService {

    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(UserTo user);

    List<User> getAll();

    void update(User user);

    void enable(int id, boolean enable);
}
