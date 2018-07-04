package ru.stqa.pft.mantis.appmanager;


import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

/**
 * Created by ishulga on 02.07.2018.
 */
public class DBHelper {

  private final org.hibernate.SessionFactory sessionFactory;

  public DBHelper() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

  }
  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from mantis_user_table t where t.username != 'administrator'").list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }
}
