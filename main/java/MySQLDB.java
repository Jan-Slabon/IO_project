import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
            conf = new Configuration().configure("DataBaseCOnf.xml").addAnnotatedClass(User.class);
            factory = conf.buildSessionFactory();
            session = factory.openSession();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean executeQuery(String query) {
        return true;
    }

    public Boolean executeQuery(String query, String[] result) {
        return true;
    }

    public Boolean setIsolationLevel(String IsolationLevel) {
        return true;
    }

    public User LogIntoAccount(String login, String Password) {
        Transaction tran = session.beginTransaction();
        User user = session.get(User.class, login);
        tran.commit();
        //MessageDigest encryptor = new MessageDigest.getInstance("SHA-256");
        if (user.getPassHash() == Password) {
            return user;
        } else return null;
    }

    public void disconect() {
        session.close();
    }
}
