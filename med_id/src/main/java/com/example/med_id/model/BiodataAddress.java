package com.example.med_id.model;

import javax.persistence.*;

@Entity
@Table(name = "m_biodata_address")
public class BiodataAddress extends CommonEntity{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @ManyToOne
    @JoinColumn(name = "biodata_id", insertable = false, updatable = false)
    public Biodata biodata;

    @Column(name = "biodata_id", nullable = true)
    private Long BiodataId;

    @Column(name = "label", length = 100, nullable = true)
    private String Label ;

    @Column(name = "recipient", length = 100, nullable = true)
    private String Recipient;

    @Column(name = "recipient_phone_number", length = 15, nullable = true)
    private String RecipientPhoneNumber;

    @ManyToOne
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    public Location location;

    @Column(name = "location_id", nullable = true)
    private Long LocationId;

    @Column(name = "postal_code", length = 10, nullable = true)
    private String PostalCode;

    @Column(columnDefinition="TEXT",name = "address", nullable = true)
    private String Address;

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

    public Long getBiodataId() {
        return BiodataId;
    }

    public void setBiodataId(Long biodataId) {
        BiodataId = biodataId;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getRecipient() {
        return Recipient;
    }

    public void setRecipient(String recipient) {
        Recipient = recipient;
    }

    public String getRecipientPhoneNumber() {
        return RecipientPhoneNumber;
    }

    public void setRecipientPhoneNumber(String recipientPhoneNumber) {
        RecipientPhoneNumber = recipientPhoneNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getLocationId() {
        return LocationId;
    }

    public void setLocationId(Long locationId) {
        LocationId = locationId;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
