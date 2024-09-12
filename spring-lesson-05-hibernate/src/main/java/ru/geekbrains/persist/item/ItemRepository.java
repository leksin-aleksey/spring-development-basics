package ru.geekbrains.persist.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

    @Query("select i from Item i " +
            "where (i.name like :name or :name is null) and " +
            "      (i.price >= :minPrice or :minPrice is null) and " +
            "      (i.price <= :maxPrice or :maxPrice is null)")
    List<Item> findWithFilter(@Param("name") String usernameFilter,
                              @Param("minPrice") Integer minAge,
                              @Param("maxPrice") Integer maxAge);

}
