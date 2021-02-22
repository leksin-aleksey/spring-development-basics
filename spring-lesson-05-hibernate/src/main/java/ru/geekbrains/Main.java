package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.*;
import ru.geekbrains.persist.customer.CustomerRepository;
import ru.geekbrains.persist.item.ItemRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

//        EntityManager em = emFactory.createEntityManager();

//        INSERT
//        em.getTransaction().begin();
//        Customer customer1 = new Customer("customer1");
//        Customer customer2 = new Customer("customer2");
//        Customer customer3 = new Customer("customer3");
//        em.persist(customer1);
//        em.persist(customer2);
//        em.persist(customer3);
//
//        Item item1 = new Item("TV", 1000);
//        Item item2 = new Item("Auto", 10000);
//        Item item3 = new Item("Phone", 100);
//        Item item4 = new Item("Service", 233);
//        Item item5 = new Item("Laptop", 880);
//        em.persist(item1);
//        em.persist(item2);
//        em.persist(item3);
//        em.persist(item4);
//        em.persist(item5);
//
//        Purchase purchase1 = new Purchase(customer1, item2, 20000);
//        Purchase purchase2 = new Purchase(customer1, item2, 10000);
//        Purchase purchase3 = new Purchase(customer1, item2, 15000);
//        Purchase purchase4 = new Purchase(customer2, item1, 1001);
//        Purchase purchase5 = new Purchase(customer3, item5, 910);
//        Purchase purchase6 = new Purchase(customer2, item4, 250);
//        Purchase purchase7 = new Purchase(customer2, item1, 999);
//        Purchase purchase8 = new Purchase(customer2, item3, 113);
//        em.persist(purchase1);
//        em.persist(purchase2);
//        em.persist(purchase3);
//        em.persist(purchase4);
//        em.persist(purchase5);
//        em.persist(purchase6);
//        em.persist(purchase7);
//        em.persist(purchase8);
//
//        em.getTransaction().commit();
//        em.close();

        CustomerRepository customerRepository = new CustomerRepository(emFactory);
        for (Object[] objArr: customerRepository.findById(1L)){
            System.out.print(objArr[0]);
            System.out.print(objArr[1]);
            System.out.print(objArr[2]);
            System.out.println();
        }

        ItemRepository itemRepository = new ItemRepository(emFactory);
        for (Object objArr : itemRepository.findById(2L)){
            System.out.print(objArr);
            System.out.println();
        }
    }
}
