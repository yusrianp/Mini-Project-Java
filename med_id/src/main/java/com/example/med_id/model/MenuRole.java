package com.example.med_id.model;

import javax.persistence.*;

@Entity
@Table(name = "m_menu_role")
public class MenuRole extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @ManyToOne
    @JoinColumn(name = "menu_id", insertable = false, updatable = false)
    public Menu menu;

    @Column(name = "menu_id", nullable = true)
    private Long MenuId;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    public Role role;

    @Column(name = "role_id", nullable = true)
    private Long RoleId;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Long getMenuId() {
        return MenuId;
    }

    public void setMenuId(Long menuId) {
        MenuId = menuId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getRoleId() {
        return RoleId;
    }

    public void setRoleId(Long roleId) {
        RoleId = roleId;
    }
}
