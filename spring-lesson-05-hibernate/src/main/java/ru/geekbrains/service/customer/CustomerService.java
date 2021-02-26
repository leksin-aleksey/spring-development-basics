package ru.geekbrains.service.customer;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<CustomerDTO> findAll();

    Page<CustomerDTO> findWithFilter(String nameFilter);

    Optional<CustomerDTO> findById(long id);

    void save(CustomerDTO customerDTO);

    void delete(long id);
}
