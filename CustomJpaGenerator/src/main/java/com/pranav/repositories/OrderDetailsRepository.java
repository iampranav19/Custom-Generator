package com.pranav.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranav.entities.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Serializable>{

}
