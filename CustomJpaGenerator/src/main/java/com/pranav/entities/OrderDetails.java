package com.pranav.entities;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ORDER_DETAILS")
@Data
public class OrderDetails {

	@Id
	@GeneratedValue(generator = "order-id-generator")
	@GenericGenerator(name = "order-id-generator", strategy = "com.pranav.generators.OrderIdGenerator")  
	@Column(name = "ORDER_ID")
	private String orderId;

	@Column(name = "ORDER_BY")
	private String orderBy;

	@Column(name = "ORDER_PLACED_DT")
	private Date orderPlaceDate;

}
