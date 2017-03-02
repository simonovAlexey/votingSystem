package by.simonow.VotingSystem;

import by.simonow.VotingSystem.matcher.ModelMatcher;
import by.simonow.VotingSystem.model.Role;
import by.simonow.VotingSystem.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static by.simonow.VotingSystem.model.BaseEntity.START_SEQ;

public class UserTestData {
    private static final Logger LOG = LoggerFactory.getLogger(UserTestData.class);

    public static final int USER_ID = START_SEQ + 3;
    public static final int ADMIN_ID = START_SEQ + 4;

    public static final User USER = new User(USER_ID, "User", "user@ya.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                    /*(comparePassword(expected.getPassword(), actual.getPassword())
                            &&*/ (Objects.equals(expected.getId(), actual.getId())
                    && Objects.equals(expected.getName(), actual.getName())
                    && Objects.equals(expected.getEmail(), actual.getEmail())
                    && Objects.equals(expected.isEnabled(), actual.isEnabled())
                    && Objects.equals(expected.getRoles(), actual.getRoles())
            )
    );

    /*private static boolean comparePassword(String rawOrEncodedPassword, String password) {
        if (PasswordUtil.isEncoded(rawOrEncodedPassword)) {
            return rawOrEncodedPassword.equals(password);
        } else if (!PasswordUtil.isMatch(rawOrEncodedPassword, password)) {
            LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
        }*/
}