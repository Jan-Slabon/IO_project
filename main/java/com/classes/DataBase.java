package com.classes;

public interface DataBase {
    Boolean connect();

    Boolean AddUserToEvent(User user, User OneAdding, Event event);

    Boolean RemoveUserFromEvent(User user, User OneRemoving, Event event);

    int CreateEvent(Event event);

    Boolean CreateAccount(User NewUser);

    User LogIntoAccount(String login, String Password);

    void disconect();

    User getUser(String Login);
}
