package com.example.med_id.model;
import javax.persistence.*;

@Entity
@Table(name = "t_doctor_office")
public class DoctorOffice extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    public Doctor doctor;

    @Column(name = "doctor_id", nullable = true)
    private Long DoctorId;

    @ManyToOne
    @JoinColumn(name = "medical_facility_id", insertable = false, updatable = false)
    public MedicalFacility medicalFacility;

    @Column(name = "medical_facility_id", nullable = true)
    private Long MedicalFacilityId;

    @Column(name = "specialization", length = 100, nullable = true)
    private String Specialization;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Long getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(Long doctorId) {
        DoctorId = doctorId;
    }

    public MedicalFacility getMedicalFacility() {
        return medicalFacility;
    }

    public void setMedicalFacility(MedicalFacility medicalFacility) {
        this.medicalFacility = medicalFacility;
    }

    public Long getMedicalFacilityId() {
        return MedicalFacilityId;
    }

    public void setMedicalFacilityId(Long medicalFacilityId) {
        MedicalFacilityId = medicalFacilityId;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }
}
