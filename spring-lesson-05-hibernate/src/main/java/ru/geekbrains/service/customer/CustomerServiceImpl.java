package ru.geekbrains.service.customer;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.customer.CustomerRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> findAll() {
        return null;
    }

    @Override
    public Page<CustomerDTO> findWithFilter(String nameFilter) {
        return null;
    }

    @Override
    public Optional<CustomerDTO> findById(long id) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public void save(CustomerDTO customerDTO) {

    }

    @Transactional
    @Override
    public void delete(long id) {

    }
}
