package com.classes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class MySQLDB implements DataBase {
    SessionFactory factory;
    Session session;
    Configuration conf;

    public MySQLDB() {
        factory = null;
        session = null;
        conf = null;
    }

    public Boolean connect() {
        try {
            conf = new Configuration().configure("DataBaseConf.xml").addAnnotatedClass(User.class).addAnnotatedClass(Event.class).addAnnotatedClass(Adres.class);
            factory = conf.buildSessionFactory();
            session = factory.openSession();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean AddUserToEvent(User user, User OneAdding, Event event) {
        if (event.Owners.contains(OneAdding) && !user.Invited.contains(event)) {
            user.Invited.add(event);
            Transaction tran = session.beginTransaction();
            session.update(user);
            tran.commit();
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Boolean RemoveUserFromEvent(User user, User OneRemoving, Event event) {
        System.out.println(event.Owners.contains(OneRemoving));
        System.out.println(user.Invited.contains(event));
        if (event.Owners.contains(OneRemoving) && user.Invited.contains(event)) {
            user.Invited.remove(event);
            Transaction tran = session.beginTransaction();
            session.update(user);
            tran.commit();
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int CreateEvent(Event event) {
        try {
            Transaction tran = session.beginTransaction();
            session.merge(event);
            tran.commit();
            return event.getEventId();
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        }
        return -1;
    }


    public Boolean CreateAccount(User user) {
        try {
            Transaction tran = session.beginTransaction();
            session.save(user);
            tran.commit();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }


    public User LogIntoAccount(String login, String Password) {
        Transaction tran = session.beginTransaction();
        User user = session.get(User.class, login);
        tran.commit();
        //MessageDigest encryptor = new MessageDigest.getInstance("SHA-256");
        if (user.getPassHash().equals(Password)) {
            return user;
        } else {
            return null;
        }
    }

    public void disconect() {
        session.close();
    }

    @Override
    public User getUser(String Login) {
        Transaction tran = session.beginTransaction();
        User user = session.get(User.class, Login);
        tran.commit();
        return user;
    }

    public Event getEvent(int id) {
        Transaction tran = session.beginTransaction();
        Event event = session.get(Event.class, id);
        tran.commit();
        return event;
    }
}
