package com.example.med_id.model;
import javax.persistence.*;

@Entity
@Table(name = "t_doctor_office_treatment")
public class DoctorOfficeTreatment extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @ManyToOne
    @JoinColumn(name = "doctor_treatment_id", insertable = false, updatable = false)
    public DoctorTreatment doctorTreatment;

    @Column(name = "doctor_treatment_id", nullable = true)
    private Long DoctorTreatmentId;

    @ManyToOne
    @JoinColumn(name = "doctor_office_id", insertable = false, updatable = false)
    public DoctorOffice doctorOffice;

    @Column(name = "doctor_office_id", nullable = true)
    private Long DoctorOfficeId;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public DoctorTreatment getDoctorTreatment() {
        return doctorTreatment;
    }

    public void setDoctorTreatment(DoctorTreatment doctorTreatment) {
        this.doctorTreatment = doctorTreatment;
    }

    public Long getDoctorTreatmentId() {
        return DoctorTreatmentId;
    }

    public void setDoctorTreatmentId(Long doctorTreatmentId) {
        DoctorTreatmentId = doctorTreatmentId;
    }

    public DoctorOffice getDoctorOffice() {
        return doctorOffice;
    }

    public void setDoctorOffice(DoctorOffice doctorOffice) {
        this.doctorOffice = doctorOffice;
    }

    public Long getDoctorOfficeId() {
        return DoctorOfficeId;
    }

    public void setDoctorOfficeId(Long doctorOfficeId) {
        DoctorOfficeId = doctorOfficeId;
    }
}
