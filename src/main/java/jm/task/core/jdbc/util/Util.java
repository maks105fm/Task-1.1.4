
package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    //JDBCI
   private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String log = "postgres";
    private static final String pass = "123456789";
    private static final String drive = "org.postgresql.Driver";
    private static final String dialect = "org.hibernate.dialect.PostgreSQLDialect";
    private static SessionFactory sessionFactory;

   public static Connection getConnectionDataBase() {
  /*      Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, log, pass);
           if (connection != null) {
                System.out.println("База данных подключена!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

   */
        return null;
   }


    public static SessionFactory getSessionFactory() {
          if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, drive);
                settings.put(Environment.URL, url);
                settings.put(Environment.USER, log);
                settings.put(Environment.PASS, pass);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("Problem creating session factory");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

// реализуйте настройку соеденения с БД
 /*
                 Properties properties = new Properties();
                properties.setProperty("hibernate.driver_class", drive);
                properties.setProperty("hibernate.connection.url", url);
                properties.setProperty("hibernate.connection.username", log);
                properties.setProperty("hibernate.connection.password", pass);
                properties.setProperty("hibernate.dialect", dialect);

                Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class).setProperties(properties);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry); */