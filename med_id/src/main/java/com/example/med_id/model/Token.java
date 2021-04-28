package com.example.med_id.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_token")
public class Token extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;

    @Column(name = "email",length = 100, nullable = true)
    private String Email;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    public User user;

    @Column(name = "user_id", nullable = true)
    private Long UserId;

    @Column(name = "token",length = 50, nullable = true)
    private String Token;

    @Column(name = "expired_on", nullable = true)
    private Date ExpiredOn;

    @Column(name = "is_expired", nullable = true)
    private Boolean IsExpired;

    @Column(name = "used_for",length = 20, nullable = true)
    private String UsedFor;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public Date getExpiredOn() {
        return ExpiredOn;
    }

    public void setExpiredOn(Date expiredOn) {
        ExpiredOn = expiredOn;
    }

    public Boolean getExpired() {
        return IsExpired;
    }

    public void setExpired(Boolean expired) {
        IsExpired = expired;
    }

    public String getUsedFor() {
        return UsedFor;
    }

    public void setUsedFor(String usedFor) {
        UsedFor = usedFor;
    }
}
