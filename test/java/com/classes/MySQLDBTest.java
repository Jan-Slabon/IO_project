package com.classes;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MySQLDBTest {

    @Test
    void BadMailTest() {
        MailTest test = new MailTest();
        boolean result = test.ValidateEmail("badmail");
        assertFalse(result);
    }

    @Test
    void GoodMailTest() {
        MailTest test = new MailTest();
        boolean result = test.ValidateEmail("goodmail@wp.pl");
        assertTrue(result);
    }

    @Test
    void CreateUserTestGoodData() {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "otherLogin1";
        String Password = "strongPassword";
        Date data = new Date(System.currentTimeMillis());
        User user = new User(login, Password, "John", "Doe", data, "dobrymail@gmail.com", null);
        boolean result = db.CreateAccount(user);
        User user2 = db.LogIntoAccount(login, Password);
        assertEquals(result, user2 != null);
        db.disconect();
    }

    @Test
    void CreateUserDuplicatedLogin() {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "otherLogin";
        String Password = "strongPassword";
        Date data = new Date(System.currentTimeMillis());
        User user1 = new User(login, Password, "John", "Doe", data, "dobrymail@gmail.com", null);
        User user2 = new User(login, Password, "John", "Doe", data, "dobrymail@gmail.com", null);
        db.CreateAccount(user1);
        boolean result = db.CreateAccount(user2);
        assertFalse(result);
        db.disconect();
    }

    @Test
    void CreateUserTestBadMail() {
        MySQLDB db = new MySQLDB();
        db.connect();
        Date data = new Date(System.currentTimeMillis());
        User user = new User("sampleLogin", "samplePassword", "John", "Doe", data, "zlastrukturamaila", null);
        boolean result = db.CreateAccount(user);
        assertFalse(result);
        db.disconect();
    }

    @Test
    void CreateEvent() {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "ownerLogin";
        String Password = "ownerPassword";
        User FirstUser = new User(login, Password, "name", "surename", null, "validmail@wp.pl", null);
        db.CreateAccount(FirstUser);
        Adres adres = new Adres("Pl", "Kato", "wojewodzka", 1, 2);
        ArrayList<User> owner = new ArrayList<User>();
        owner.add(FirstUser);
        Event event = new Event(0, adres, "Impreza", "opis", owner);
        int result = db.CreateEvent(event);
        assertTrue(result >= 0);
        db.disconect();
    }

    @Test
    void AddUserToEventByEventOwner() {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "ownerLogin1";
        String Password = "ownerPassword";
        String userLogin = "userLogin";
        String userPassword = "userPassword";
        User FirstUser = new User(login, Password, "name", "surename", null, "validmail@wp.pl", null);
        User SecondUser = new User(userLogin, userPassword, "name", "surename", null, "validmail@wp.pl", null);
        db.CreateAccount(FirstUser);
        db.CreateAccount(SecondUser);
        Adres adres = new Adres("Pl", "Kato", "wojewodzka", 1, 2);
        ArrayList<User> owner = new ArrayList<User>();
        owner.add(FirstUser);
        Event event = new Event(0, adres, "Impreza", "opis", owner);
        db.CreateEvent(event);
        boolean result = db.AddUserToEvent(SecondUser, FirstUser, event);
        assertTrue(result);
        db.disconect();
    }

    @Test
    void AddUserToEventByStranger() {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "ownerLogin2";
        String Password = "ownerPassword";
        String userLogin = "userLogin1";
        String userPassword = "userPassword";
        String thirdLogin = "anotherLogin";
        String ThirdPassword = "anotherPassword";
        User FirstUser = new User(login, Password, "name", "surename", null, "validmail@wp.pl", null);
        User SecondUser = new User(userLogin, userPassword, "name", "surename", null, "validmail@wp.pl", null);
        User ThirdUser = new User(thirdLogin, ThirdPassword, "name", "surename", null, "validmail@wp.pl", null);
        db.CreateAccount(FirstUser);
        db.CreateAccount(SecondUser);
        db.CreateAccount(ThirdUser);
        Adres adres = new Adres("Pl", "Kato", "wojewodzka", 1, 2);
        ArrayList<User> owner = new ArrayList<User>();
        owner.add(FirstUser);
        Event event = new Event(0, adres, "Impreza", "opis", owner);
        db.CreateEvent(event);
        boolean result = db.AddUserToEvent(ThirdUser, SecondUser, event);
        assertFalse(result);
        db.disconect();
    }

    @Test
    void RemoveUserFromEventByOwner() throws Exception {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "ownerLogin3";
        String Password = "ownerPassword";
        String userLogin = "userLogin2";
        String userPassword = "userPassword";
        User user1 = new User(login, Password, "name", "surename", null, "validmail@wp.pl", null);
        User user2 = new User(userLogin, userPassword, "name", "surename", null, "validmail@wp.pl", null);
        db.CreateAccount(user1);
        db.CreateAccount(user2);
        Adres adres = new Adres("Pl", "Kato", "wojewodzka", 1, 2);
        ArrayList<User> owner = new ArrayList<User>();
        owner.add(user1);
        Event event = new Event(0, adres, "Impreza", "opis", owner);
        int id = db.CreateEvent(event);
        Thread.sleep(5000);
        Event newEvent = db.getEvent(id);
        System.out.println(newEvent == null);
        db.AddUserToEvent(user2, user1, event);
        boolean result = db.RemoveUserFromEvent(user2, user1, event);
        assertTrue(result);
        db.disconect();
    }

    @Test
    void RemoveUserFromEventByStranger() {
        MySQLDB db = new MySQLDB();
        db.connect();
        String login = "ownerLogin4";
        String Password = "ownerPassword";
        String userLogin = "userLogin3";
        String userPassword = "userPassword";
        String thirdLogin = "anotherLogin1";
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
        Event event = new Event(0, adres, "Impreza", "opis", owner);
        db.CreateEvent(event);
        db.AddUserToEvent(user3, user1, event);
        boolean result = db.RemoveUserFromEvent(user3, user2, event);
        assertFalse(result);
        db.disconect();
    }
}