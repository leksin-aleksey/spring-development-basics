package ru.geekbrains.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserRepository implements AutoCloseable{

//    private final EntityManagerFactory emFactory;
    private final EntityManager em;

    public UserRepository(EntityManager em){
        this.em = em;
    }
    public List<User> findAll() {
        return em.createQuery("from User", User.class)
                .getResultList();
    }

    public User findById(long id) {
        return em.find(User.class, id);
    }

    public void insert(User user) {
        if (user.getId() != null){
            user.setId(null);
        }
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public void update(User user) {
        User newUser = em.find(User.class, user.getId());
        if (newUser != null) {
            em.getTransaction().begin();
//            em.persist(newUser);
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
            newUser.setEmail(user.getEmail());
            em.getTransaction().commit();
        } else {
            insert(user);
        }
    }

    public void delete(long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }
    }

    @Override
    public void close() throws Exception {
        if (em != null && em.isOpen()){
            em.close();
        }
    }
}
