package com.example.med_id.model;

import javax.persistence.*;

@Entity
@Table(name = "m_blood_group")
public class BloodGroup extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;

    @Column(name = "code", length = 5, nullable = true)
    private String Code;

    @Column(name = "description", nullable = true)
    private String Description;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
