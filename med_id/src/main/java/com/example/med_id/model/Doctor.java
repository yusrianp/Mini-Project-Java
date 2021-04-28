package com.example.med_id.model;

import javax.persistence.*;

@Entity
@Table(name = "m_doctor")
public class Doctor extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "biodata_id", insertable = false, updatable = false)
    public Biodata biodata;

    @Column(name = "biodata_id", nullable = true)
    private Long BiodataId;

    @Column(name = "str", length = 50, nullable = true)
    private String Str;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
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

    public String getStr() {
        return Str;
    }

    public void setStr(String str) {
        Str = str;
    }
}
