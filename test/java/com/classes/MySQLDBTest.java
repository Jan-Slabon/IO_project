package com.classes;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MySQLDBTest {
    @Test
    void CreateUserTestGoodData() {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "otherLogin";
        String Password = "strongPassword";
        Date data = new Date(System.currentTimeMillis());
        boolean result = db.CreateAccount(login, Password, "John", "Doe", data, "dobrymail@gmail.com", null);
        User user = db.LogIntoAccount(login, Password);
        assertEquals(result, user != null);
        db.disconect();
    }

    @Test
    void CreateUserDuplicatedLogin() {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "otherLogin";
        String Password = "strongPassword";
        Date data = new Date(System.currentTimeMillis());
        db.CreateAccount(login, Password, "John", "Doe", data, "dobrymail@gmail.com", null);
        boolean result = db.CreateAccount(login, Password, "John", "Doe", data, "dobrymail@gmail.com", null);
        assertFalse(result);
        db.disconect();
    }

    @Test
    void CreateUserTestBadMail() {
        MySQLDB db = new MySQLDB();
        db.connect();
        Date data = new Date(System.currentTimeMillis());
        boolean result = db.CreateAccount("sampleLogin", "samplePassword", "John", "Doe", data, "zlastrukturamaila", null);
        assertFalse(result);
        db.disconect();
    }

    @Test
    void CreateEvent() {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "ownerLogin";
        String Password = "ownerPassword";
        db.CreateAccount(login, Password, "name", "surename", null, "validmail@wp.pl", null);
        int id = db.CreateEvent(new Date(System.currentTimeMillis()), "Street Name,Exact Adress ,City name", "This is my event", login);
        assertTrue(id > 0);
        db.disconect();
    }

    @Test
    void AddUserToEventByEventOwner() {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "ownerLogin";
        String Password = "ownerPassword";
        String userLogin = "userLogin";
        String userPassword = "userPassword";
        db.CreateAccount(login, Password, "name", "surename", null, "validmail@wp.pl", null);
        db.CreateAccount(userLogin, userPassword, "name", "surename", null, "validmail@wp.pl", null);
        int id = db.CreateEvent(new Date(System.currentTimeMillis()), "Street Name,Exact Adress ,City name", "This is my event", login);
        boolean result = db.AddUserToEvent(userLogin, login, Password, id);
        assertTrue(result);
        db.disconect();
    }

    @Test
    void AddUserToEventByStranger() {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "ownerLogin";
        String Password = "ownerPassword";
        String userLogin = "userLogin";
        String userPassword = "userPassword";
        String thirdLogin = "anotherLogin";
        String ThirdPassword = "anotherPassword";
        User FirstUser = new User(login, Password, "name", "surename", null, "validmail@wp.pl", null);
        User SecondUser = new User(userLogin, userPassword, "name", "surename", null, "validmail@wp.pl", null);
        User ThirdUser = new User(thirdLogin, ThirdPassword, "name", "surename", null, "validmail@wp.pl", null);
        db.CreateAccount(FirstUser);
        db.CreateAccount(SecondUser);
        db.CreateAccount(ThirdUser);
        Event event = new Event();
        int id = db.CreateEvent(event);
        boolean result = db.AddUserToEvent(ThirdUser, SecondUser, event);
        assertFalse(result);
        db.disconect();
    }

    @Test
    void RemoveUserFromEventByOwner() {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "ownerLogin";
        String Password = "ownerPassword";
        String userLogin = "userLogin";
        String userPassword = "userPassword";
        db.CreateAccount(login, Password, "name", "surename", null, "validmail@wp.pl", null);
        db.CreateAccount(userLogin, userPassword, "name", "surename", null, "validmail@wp.pl", null);
        int id = db.CreateEvent(new Date(System.currentTimeMillis()), "Street Name,Exact Adress ,City name", "This is my event", login);
        db.AddUserToEvent(userLogin, login, Password, id);
        boolean result = db.RemoveUserFromEvent(userLogin, login, Password, id);
        assertTrue(result);
        db.disconect();
    }

    @Test
    void RemoveUserFromEventByStranger() {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "ownerLogin";
        String Password = "ownerPassword";
        String userLogin = "userLogin";
        String userPassword = "userPassword";
        String thirdLogin = "anotherLogin";
        String ThirdPassword = "anotherPassword";
        User user1 = new User(login, Password, "name", "surename", null, "validmail@wp.pl", null);
        User user2 = new User(userLogin, userPassword, "name", "surename", null, "validmail@wp.pl", null);
        User user3 = new User(thirdLogin, ThirdPassword, "name", "surename", null, "validmail@wp.pl", null);
        db.CreateAccount(user1);
        db.CreateAccount(user2);
        db.CreateAccount(user3);
        Adres adres = new Adres("Pl", "Kato", "wojewodzka", 1, 2);
        ArrayList<User> owner = new ArrayList<User>();
        owner.add(user1);
        Event event = new Event(0, adres, "Impreza", "opis", owner, null);
        db.CreateEvent(event);
        db.AddUserToEvent(user1, user3, event);
        boolean result = db.RemoveUserFromEvent(user3, user2, event);
        assertFalse(result);
        db.disconect();
    }
}