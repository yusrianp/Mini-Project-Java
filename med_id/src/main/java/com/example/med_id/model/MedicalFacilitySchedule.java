package com.example.med_id.model;
import javax.persistence.*;

@Entity
@Table(name = "m_medical_facility_schedule")
public class MedicalFacilitySchedule extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long Id;

    @ManyToOne
    @JoinColumn(name = "medical_facility_id", insertable = false, updatable = false)
    public MedicalFacility medicalFacility ;

    @Column(name = "medical_facility_id", nullable = true)
    private Long medical_facility_id;

    @Column(name = "day", length = 10, nullable = true)
    private String Day;

    @Column(name = "time_schedule_start", length = 10, nullable = true)
    private String TimeScheduleStart;

    @Column(name = "time_schedule_end", length = 10, nullable = true)
    private String TimeScheduleEnd;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public MedicalFacility getMedicalFacility() {
        return medicalFacility;
    }

    public void setMedicalFacility(MedicalFacility medicalFacility) {
        this.medicalFacility = medicalFacility;
    }

    public Long getMedical_facility_id() {
        return medical_facility_id;
    }

    public void setMedical_facility_id(Long medical_facility_id) {
        this.medical_facility_id = medical_facility_id;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getTimeScheduleStart() {
        return TimeScheduleStart;
    }

    public void setTimeScheduleStart(String timeScheduleStart) {
        TimeScheduleStart = timeScheduleStart;
    }

    public String getTimeScheduleEnd() {
        return TimeScheduleEnd;
    }

    public void setTimeScheduleEnd(String timeScheduleEnd) {
        TimeScheduleEnd = timeScheduleEnd;
    }
}
