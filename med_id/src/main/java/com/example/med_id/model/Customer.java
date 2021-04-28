package com.example.med_id.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "m_customer")
public class Customer extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @ManyToOne
    @JoinColumn(name = "biodata_id", insertable = false, updatable = false)
    public Biodata biodata;

    @Column(name = "biodata_id", nullable = true)
    private Long BiodataId;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dob", nullable = true)
    private Date Dob;

    @Column(name = "gender", length = 1, nullable = true)
    private String Gender;


    @ManyToOne
    @JoinColumn(name = "blood_group_id", insertable = false, updatable = false)
    public BloodGroup BloodGroup;

    @Column(name = "blood_group_id", nullable = true)
    private Long BloodGroupId;

    @Column(name = "rhesus_type", length = 5, nullable = true)
    private String RhesusType;

    @Column(name = "height", nullable = true)
    private double Height;

    @Column(name = "weight", nullable = true)
    private double Weight;

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

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date dob) {
        Dob = dob;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public com.example.med_id.model.BloodGroup getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(com.example.med_id.model.BloodGroup bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public Long getBloodGroupId() {
        return BloodGroupId;
    }

    public void setBloodGroupId(Long bloodGroupId) {
        BloodGroupId = bloodGroupId;
    }

    public String getRhesusType() {
        return RhesusType;
    }

    public void setRhesusType(String rhesusType) {
        RhesusType = rhesusType;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }
}
