package ru.geekbrains.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserRepository {

//    private final EntityManagerFactory emFactory;
    private final EntityManager em;

    public UserRepository(EntityManagerFactory emFactory) {
//        this.emFactory = emFactory;
        em = emFactory.createEntityManager();
    }

    public List<User> findAll() {
        return em.createQuery("from User", User.class)
                .getResultList();
    }

    public User findById(long id) {
        return null;
    }

    public void insert(User user) {

    }

    public void update(User user) {

    }

    public void delete(long id) {

    }

}
