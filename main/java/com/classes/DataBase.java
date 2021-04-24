package com.classes;

public interface DataBase {
    Boolean connect();

    Boolean executeQuery(String query);

    Boolean executeQuery(String query, String[] result);

    Boolean setIsolationLevel(String IsolationLevel);

    User LogIntoAccount(String login, String Password);

    void disconect();
}