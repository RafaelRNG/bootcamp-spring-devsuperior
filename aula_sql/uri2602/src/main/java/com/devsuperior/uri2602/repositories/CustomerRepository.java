package com.devsuperior.uri2602.repositories;

import com.devsuperior.uri2602.dtos.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT name FROM customers WHERE UPPER(state) = UPPER(:state)", nativeQuery = true)
    List<CustomerMinProjection> searchSQL(String state);

    @Query(value = "SELECT new com.devsuperior.uri2602.dtos.CustomerMinDTO(obj.name) FROM Customer obj WHERE UPPER(obj.state) = UPPER(:state)")
    List<CustomerMinDTO> searchJPQL(String state);
}