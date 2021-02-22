package ru.geekbrains.persist.item;

import ru.geekbrains.persist.item.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ItemRepository {

    private final EntityManagerFactory emFactory;

    public ItemRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<Item> findAll() {
        return executeForEntityManager(
                em -> em.createNamedQuery("allItems", Item.class).getResultList()
        );
    }

    public List<Object[]> findById(long id) {
        EntityManager em = emFactory.createEntityManager();
//        Query query = em.createQuery("select c.name, i.name, p.purchase from Purchase p inner join Customer c on c.id = p.customer inner join Item i on i.id = p.item where c.id = :id");
//        Query query = em.createQuery("from Customer c, Purchase p where c.id = :id");
        Query query = em.createQuery(
                "select distinct c " +
                        "from Purchase p " +
                        "inner join Customer c on c.id = p.customer.id " +
                        "inner join Item i on i.id = p.item.id " +
                        "where i.id = :id");
        query.setParameter("id", id);
        return query.getResultList();

//        return executeForEntityManager(
////                em -> em.find(Customer.class, id)
//                em -> em.createQuery("select  from Purchase p inner join Customer c on c.id = p.customer_id inner join Item i on i.id = p.item_id where ")
//        );
    }

    public void insert(Item item) {
        executeInTransaction(em -> em.persist(item));
    }

    public void update(Item item) {
        executeInTransaction(em -> em.merge(item));
    }

    public void delete(long id) {
        executeInTransaction(em -> {
            Item item = em.find(Item.class, id);
            if (item != null) {
                em.remove(item);
            }
        });
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
