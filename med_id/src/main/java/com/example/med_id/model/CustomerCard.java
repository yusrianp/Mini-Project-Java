package com.example.med_id.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_customer_registered_card")
public class CustomerCard extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    public Customer customer;

    @Column(name = "customer_id", nullable = true)
    private Long CustomerId;

    @Column(name = "card_number", length = 20, nullable = true)
    private String CardNumber;

    @Column(name = "validity_period", nullable = true)
    private Date ValidityPeriod;

    @Column(name = "cvv", length = 5, nullable = true)
    private String Cvv;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Long customerId) {
        CustomerId = customerId;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public Date getValidityPeriod() {
        return ValidityPeriod;
    }

    public void setValidityPeriod(Date validityPeriod) {
        ValidityPeriod = validityPeriod;
    }

    public String getCvv() {
        return Cvv;
    }

    public void setCvv(String cvv) {
        Cvv = cvv;
    }
}

