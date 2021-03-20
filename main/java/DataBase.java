public interface DataBase {
    Boolean connect();

    Boolean executeQuery(String query);

    Boolean executeQuery(String query, String[] result);

    Boolean setIsolationLevel(String IsolationLevel);

    Boolean LogIntoAccount(String login, String Password);
}
