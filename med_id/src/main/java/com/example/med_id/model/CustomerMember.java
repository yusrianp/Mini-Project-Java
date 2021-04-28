package com.example.med_id.model;
import javax.persistence.*;

@Entity
@Table(name = "m_customer_member")
public class CustomerMember extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @OneToOne
    @JoinColumn(name = "parent_biodata_id", insertable = false, updatable = false)
    public Biodata biodata;

    @Column(name = "parent_biodata_id", nullable = true)
    private Long ParentBiodataId;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    public Customer customer;

    @Column(name = "customer_id", nullable = true)
    private Long CustomerId;

    @OneToOne
    @JoinColumn(name = "customer_relation_id", insertable = false, updatable = false)
    public CustomerRelation customerRelation;

    @Column(name = "customer_relation_id", nullable = true)
    private Long CustomerRelationId;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Biodata getBiodata() {
        return biodata;
    }

    public void setBiodata(Biodata biodata) {
        this.biodata = biodata;
    }

    public Long getParentBiodataId() {
        return ParentBiodataId;
    }

    public void setParentBiodataId(Long parentBiodataId) {
        ParentBiodataId = parentBiodataId;
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

    public CustomerRelation getCustomerRelation() {
        return customerRelation;
    }

    public void setCustomerRelation(CustomerRelation customerRelation) {
        this.customerRelation = customerRelation;
    }

    public Long getCustomerRelationId() {
        return CustomerRelationId;
    }

    public void setCustomerRelationId(Long customerRelationId) {
        CustomerRelationId = customerRelationId;
    }
}
