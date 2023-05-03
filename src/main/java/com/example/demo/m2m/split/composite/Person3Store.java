package com.example.demo.m2m.split.composite;

import com.example.demo.m2m.split.separate.Person2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Person3Store extends JpaRepository<Person3, Long> {
    @Query("select p from Person3 p inner join p.associations associations where associations.address.street = ?1")
    List<Person3> findByAssociations_Address_Street(String street);
    List<Person3> findByAssociations_PersonAddressKey_PersonId(Long personId);
    @Query("select p from Person3 p where p.registrationNumber = ?1")
    Person3 findByRegistrationNumber(String registrationNumber);

    //Person3 findByRegistrationNumber(String registrationNumber);
}
