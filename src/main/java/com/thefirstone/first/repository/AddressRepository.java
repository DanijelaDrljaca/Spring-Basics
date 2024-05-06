package com.thefirstone.first.repository;

import com.thefirstone.first.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AddressRepository extends JpaRepository<Address, Long> {

//    @Query("SELECT a FROM User u JOIN Address WHERE u.address = a.id AND u.id =:userId")
    @Query("SELECT a FROM User u JOIN Address a ON u.address.id = a.id WHERE u.id =:userId")
    Address findByUserId(Long userId);
}
