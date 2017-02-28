package by.simonow.VotingSystem;


import by.simonow.VotingSystem.model.BaseEntity;

public class AuthorizedUser {
    public static int id = BaseEntity.START_SEQ;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

}
