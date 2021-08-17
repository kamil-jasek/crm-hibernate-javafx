package pl.sda.crm.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.crm.entity.TestEntity;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            final var configuration = new Configuration();
            configuration.setProperties(loadHibernateProperties());
            configureEntities(configuration);
            sessionFactory = configuration.buildSessionFactory();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex.getMessage(), ex);
        }
    }

    private static void configureEntities(Configuration configuration) {
        configuration.addAnnotatedClass(TestEntity.class);
    }

    private static Properties loadHibernateProperties() throws IOException {
        final var properties = new Properties();
        properties.load(HibernateUtil.class.getClassLoader()
                .getResourceAsStream("hibernate.properties"));
        return properties;
    }
}
