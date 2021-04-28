package com.example.med_id.model;
import javax.persistence.*;

@Entity
@Table(name = "m_medical_facility")
public class MedicalFacility extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @Column(name = "name", length = 50, nullable = true)
    private String Name;

    @ManyToOne
    @JoinColumn(name = "medical_facility_category_id", insertable = false, updatable = false)
    public MedicalFacilityCategory medicalFacilityCategory;

    @Column(name = "medical_facility_category_id", nullable = true)
    private Long MedicalFacilityCategoryId;

    @ManyToOne
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    public Location location ;

    @Column(name = "location_id", nullable = true)
    private Long LocationId;

    @Column(columnDefinition="TEXT",name = "full_address", nullable = true)
    private String FullAddress;

    @Column(name = "email", length = 100, nullable = true)
    private String Email;

    @Column(name = "phone_code", length = 10, nullable = true)
    private String PhoneCode;

    @Column(name = "phone", length = 15, nullable = true)
    private String Phone;

    @Column(name = "fax", length = 15, nullable = true)
    private String Fax;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public MedicalFacilityCategory getMedicalFacilityCategory() {
        return medicalFacilityCategory;
    }

    public void setMedicalFacilityCategory(MedicalFacilityCategory medicalFacilityCategory) {
        this.medicalFacilityCategory = medicalFacilityCategory;
    }

    public Long getMedicalFacilityCategoryId() {
        return MedicalFacilityCategoryId;
    }

    public void setMedicalFacilityCategoryId(Long medicalFacilityCategoryId) {
        MedicalFacilityCategoryId = medicalFacilityCategoryId;
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

    public String getFullAddress() {
        return FullAddress;
    }

    public void setFullAddress(String fullAddress) {
        FullAddress = fullAddress;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneCode() {
        return PhoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        PhoneCode = phoneCode;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }
}
