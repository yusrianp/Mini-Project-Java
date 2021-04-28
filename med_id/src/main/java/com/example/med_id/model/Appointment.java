package com.example.med_id.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_appointment")
public class Appointment extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    public Customer customer;

    @Column(name = "customer_id", nullable = true)
    private Long CustomerId;

    @ManyToOne
    @JoinColumn(name = "doctor_office_id", insertable = false, updatable = false)
    public DoctorOffice doctorOffice;

    @Column(name = "doctor_office_id", nullable = true)
    private Long DoctorOfficeId;

    @ManyToOne
    @JoinColumn(name = "doctor_office_schedule_id", insertable = false, updatable = false)
    public DoctorOfficeSchedule doctorOfficeSchedule;

    @Column(name = "doctor_office_schedule_id", nullable = true)
    private Long DoctorOfficeScheduleId;

    @ManyToOne
    @JoinColumn(name = "doctor_office_treatment_id", insertable = false, updatable = false)
    public DoctorOfficeTreatment doctorOfficeTreatment;

    @Column(name = "doctor_office_treatment_id", nullable = true)
    private Long DoctorOfficeTreatmentId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy/MM/dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_date", nullable = true)
    private Date AppointmentDate;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Long customerId) {
        CustomerId = customerId;
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

    public DoctorOfficeSchedule getDoctorOfficeSchedule() {
        return doctorOfficeSchedule;
    }

    public void setDoctorOfficeSchedule(DoctorOfficeSchedule doctorOfficeSchedule) {
        this.doctorOfficeSchedule = doctorOfficeSchedule;
    }

    public Long getDoctorOfficeScheduleId() {
        return DoctorOfficeScheduleId;
    }

    public void setDoctorOfficeScheduleId(Long doctorOfficeScheduleId) {
        DoctorOfficeScheduleId = doctorOfficeScheduleId;
    }

    public DoctorOfficeTreatment getDoctorOfficeTreatment() {
        return doctorOfficeTreatment;
    }

    public void setDoctorOfficeTreatment(DoctorOfficeTreatment doctorOfficeTreatment) {
        this.doctorOfficeTreatment = doctorOfficeTreatment;
    }

    public Long getDoctorOfficeTreatmentId() {
        return DoctorOfficeTreatmentId;
    }

    public void setDoctorOfficeTreatmentId(Long doctorOfficeTreatmentId) {
        DoctorOfficeTreatmentId = doctorOfficeTreatmentId;
    }

    public Date getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        AppointmentDate = appointmentDate;
    }
}
