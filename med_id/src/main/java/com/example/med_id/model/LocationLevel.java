package com.example.med_id.model;
import javax.persistence.*;

@Entity
@Table(name = "m_location_level")
public class LocationLevel extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @Column(name = "name", length = 50, nullable = true)
    private String Name;

    @Column(name = "abbreviation", length = 50, nullable = true)
    private String Abbreviation;

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

    public String getAbbreviation() {
        return Abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        Abbreviation = abbreviation;
    }
}
