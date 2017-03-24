package by.simonow.VotingSystem.util;


import by.simonow.VotingSystem.model.Role;
import by.simonow.VotingSystem.model.User;
import by.simonow.VotingSystem.model.Votes;
import by.simonow.VotingSystem.to.UserTo;

public class UserUtil {


    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getName(), newUser.getEmail().toLowerCase(), newUser.getPassword(), Role.ROLE_USER);
    }

    public static UserTo asTo(User user, Votes vote) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword(),vote);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setPassword(user.getPassword());
//        user.setPassword(PasswordUtil.encode(user.getPassword())); //TODO  after Spring Security
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
