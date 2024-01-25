package com.jbnucpu.www.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class StudyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String studentNumber; // 작성자

    private String name; // 팀장명

    private String grade;

    private String college;

    private String department;

    private String phoneNumber;

    private String studySubject;

    private String studyGoal;

    private String studyMethod; // 스터디 활동 방식

    private String studyReference; // 스터디 자료

    private String studyTime;

    private String studyPlace;

    private String managerExperience;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @OneToMany(mappedBy = "studyEntity",cascade = CascadeType.ALL)
    private Set<EnrollEntity> enrollEntities_Study = new HashSet<EnrollEntity>();
}
