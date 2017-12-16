import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.mart.entity.Product;




public class DaoTest {
    SessionFactory sessionFactory;

    public DaoTest() {
        AnnotationConfiguration config=new AnnotationConfiguration();
        config.setProperty("hibernate.connection.url","jdbc:postgresql://127.0.0.1:5432/dbname");
        config.setProperty("hibernate.connection.username","username");
        config.setProperty("hibernate.connection.driver_class","org.postgresql.Driver");
        config.setProperty("hibernate.current_session_context_class", "thread");
        config.setProperty("hibernate.show_sql", "false");
        config.addAnnotatedClass(Product.class);

        sessionFactory=config.buildSessionFactory();
    }

    public Session getSession()
    {
        Session session;

        try {
            session = sessionFactory.getCurrentSession();
        } catch (SessionException se) {
            session = sessionFactory.openSession();
        }

        return session;
    }
}