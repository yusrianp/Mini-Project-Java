package com.example.med_id.model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "m_location")
public class Location extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @Column(name = "name", length = 100, nullable = true)
    private String Name;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "parent_id") //Implement self-association based on the parent menu ID (internal is one-to-many)
    public List<Location> locations;

    @Column(name = "parent_id", nullable = true)
    private Long ParentId;

    @ManyToOne
    @JoinColumn(name = "location_level_id", insertable = false, updatable = false)
    public LocationLevel locationLevel;

    @Column(name = "location_level_id", nullable = true)
    private Long LocationLevelId;

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

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Long getParentId() {
        return ParentId;
    }

    public void setParentId(Long parentId) {
        ParentId = parentId;
    }

    public LocationLevel getLocationLevel() {
        return locationLevel;
    }

    public void setLocationLevel(LocationLevel locationLevel) {
        this.locationLevel = locationLevel;
    }

    public Long getLocationLevelId() {
        return LocationLevelId;
    }

    public void setLocationLevelId(Long locationLevelId) {
        LocationLevelId = locationLevelId;
    }
}
