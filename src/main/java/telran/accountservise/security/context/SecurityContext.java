package telran.accountservise.security.context;

public interface SecurityContext {
    boolean addUser(UserProfile user);
    UserProfile removeUser(String login);
    UserProfile getUser(String login);
}
