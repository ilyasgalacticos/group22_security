package kz.bitlab.group22.beans;

import kz.bitlab.group22.entities.Roles;
import kz.bitlab.group22.entities.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserBeanImpl implements UserBean {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Users getUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Users user = (Users)session.createQuery("SELECT u FROM Users u WHERE u.email = :poshta AND u.deletedAt = null")
                .setParameter("poshta", email).uniqueResult();
        session.close();
        return user;
    }

    @Override
    public void addUser(Users user){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        user.setCreatedAt(new Date());
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public Roles getRole(Long id) {
        Session session = sessionFactory.openSession();
        Roles role = (Roles)session.createQuery("SELECT r FROM Roles r WHERE r.id = :idhska AND r.deletedAt = null")
                .setParameter("idhska", id).uniqueResult();
        session.close();
        return role;
    }
}
