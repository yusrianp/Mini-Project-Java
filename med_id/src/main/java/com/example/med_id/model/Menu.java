package com.example.med_id.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "m_menu")
public class Menu extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @Column(name = "name", length = 20, nullable = true)
    private String Name;

    @Column(name = "url", length = 50, nullable = true)
    private String Url;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "parent_id") //Implement self-association based on the parent menu ID (internal is one-to-many)
    public List<Menu> menus;

    @Column(name = "parent_id", nullable = true)
    private Long ParentId;

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

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Long getParentId() {
        return ParentId;
    }

    public void setParentId(Long parentId) {
        ParentId = parentId;
    }
}
