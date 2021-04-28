package com.example.med_id.model;
import javax.persistence.*;

@Entity
@Table(name = "t_doctor_office_schedule")
public class DoctorOfficeSchedule extends CommonEntity{
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
    @JoinColumn(name = "medical_facility_schedule_id", insertable = false, updatable = false)
    public MedicalFacilitySchedule medicalFacilitySchedule;

    @Column(name = "medical_facility_schedule_id", nullable = true)
    private Long MedicalFacilityScheduleId;

    @Column(name = "slot", nullable = true)
    private long Slot;

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

    public MedicalFacilitySchedule getMedicalFacilitySchedule() {
        return medicalFacilitySchedule;
    }

    public void setMedicalFacilitySchedule(MedicalFacilitySchedule medicalFacilitySchedule) {
        this.medicalFacilitySchedule = medicalFacilitySchedule;
    }

    public Long getMedicalFacilityScheduleId() {
        return MedicalFacilityScheduleId;
    }

    public void setMedicalFacilityScheduleId(Long medicalFacilityScheduleId) {
        MedicalFacilityScheduleId = medicalFacilityScheduleId;
    }

    public long getSlot() {
        return Slot;
    }

    public void setSlot(long slot) {
        Slot = slot;
    }
}
