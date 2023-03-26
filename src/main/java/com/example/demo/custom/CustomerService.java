package com.example.demo.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Customer ... customers) {
        Arrays.stream(customers).forEach(customer -> {
            Customer customerResult =  customerRepository.save(customer);
            log.info("save customer with ID --------> {}", customerResult.getId());
        });
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCustomer(Customer customer) {

        Customer customerResult =  customerRepository.save(customer);
        log.info("save customer with ID --------> {}", customerResult.getId());

    }

    public List<CustomerTypeCount> totalCustomersByType() {
        return customerRepository.totalCustomersByType();
    }

//    public List<Object[]> totalCustomersByType2() {
//        return customerRepository.totalCustomersByType2();
////        return null;
//    }
}
