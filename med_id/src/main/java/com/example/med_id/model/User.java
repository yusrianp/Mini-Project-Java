package com.example.med_id.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "m_user")
public class User extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "biodata_id", insertable = false, updatable = false)
    public Biodata biodata;

    @Column(name = "biodata_id", nullable = true)
    private Long BiodataId;

    @OneToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    public Role role;

    @Column(name = "role_id", nullable = true)
    private Long RoleId;

    @Column(name = "email", length = 100, unique = true)
    private String Email;

    @Column(name = "password", nullable = true)
    private String Password;

    @Column(name = "login_attempt", nullable = true)
    private Integer LoginAttempt;

    @Column(name = "is_locked", nullable = true)
    private Boolean IsLocked;

    @Column(name = "last_login", nullable = true)
    private Date LastLogin;

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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getLoginAttempt() {
        return LoginAttempt;
    }

    public void setLoginAttempt(Integer loginAttempt) {
        LoginAttempt = loginAttempt;
    }

    public Boolean getLocked() {
        return IsLocked;
    }

    public void setLocked(Boolean locked) {
        IsLocked = locked;
    }

    public Date getLastLogin() {
        return LastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        LastLogin = lastLogin;
    }
}
