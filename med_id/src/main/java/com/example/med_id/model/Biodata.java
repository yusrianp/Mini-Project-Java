package com.example.med_id.model;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "m_biodata")
public class Biodata extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;

    @Column(name = "fullname")
    private String Fullname;

    @Column(name = "mobile_phone", length = 15, nullable = true)
    private String MobilePhone;

    @Lob
    @Column(name = "image")
    private Blob Image;

    @Column(name = "image_path")
    private String ImagePath;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public Blob getImage() {
        return Image;
    }

    public void setImage(Blob image) {
        Image = image;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}
