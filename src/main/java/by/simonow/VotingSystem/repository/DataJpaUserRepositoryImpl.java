package by.simonow.VotingSystem.repository;

import by.simonow.VotingSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository {
    private static final Sort SORT_NAME_EMAIL = new Sort("name", "email");

    @Autowired
    private ProxyUserRepository proxyUserRepository;

    @Override
    public User save(User user) {
        return proxyUserRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return proxyUserRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return proxyUserRepository.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return proxyUserRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return proxyUserRepository.findAll(SORT_NAME_EMAIL);
    }

}
