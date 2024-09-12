package ru.geekbrains.persist.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.persist.item.Item;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @Query("select c from Customer c " +
            "where c.name like :name or :name is null")
    List<Customer> findWithFilter(@Param("name") String nameFilter);

}
