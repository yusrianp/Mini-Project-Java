package com.example.med_id.model;

import javax.persistence.*;

@Entity
@Table(name = "m_role")
public class Role extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;

    @Column(name = "name",length = 20, nullable = true)
    private String Name;

    @Column(name = "code",length = 20, nullable = true)
    private String Code;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
