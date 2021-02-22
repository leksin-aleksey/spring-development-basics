package ru.geekbrains.persist.customer;

import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.customer.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
public class CustomerRepository {

    private final EntityManagerFactory emFactory;

    public CustomerRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<Customer> findAll() {
        return executeForEntityManager(
                em -> em.createNamedQuery("allCustomers", Customer.class).getResultList()
        );
    }

    public List<Object[]> findById(long id) {
        EntityManager em = emFactory.createEntityManager();
//        Query query = em.createQuery("select c.name, i.name, p.purchase from Purchase p inner join Customer c on c.id = p.customer inner join Item i on i.id = p.item where c.id = :id");
//        Query query = em.createQuery("from Customer c, Purchase p where c.id = :id");
        Query query = em.createQuery(
                "select c, p, i " +
                        "from Purchase p " +
                        "inner join Customer c on c.id = p.customer.id " +
                        "inner join Item i on i.id = p.item.id " +
                        "where c.id = :id");
        query.setParameter("id", id);
        return query.getResultList();

//        return executeForEntityManager(
////                em -> em.find(Customer.class, id)
//                em -> em.createQuery("select  from Purchase p inner join Customer c on c.id = p.customer_id inner join Item i on i.id = p.item_id where ")
//        );
    }

    public void insert(Customer customer) {
        executeInTransaction(em -> em.persist(customer));
    }

    public void update(Customer customer) {
        executeInTransaction(em -> em.merge(customer));
    }

    public void delete(long id) {
        executeInTransaction(em -> {
            Customer customer = em.find(Customer.class, id);
            if (customer != null) {
                em.remove(customer);
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
