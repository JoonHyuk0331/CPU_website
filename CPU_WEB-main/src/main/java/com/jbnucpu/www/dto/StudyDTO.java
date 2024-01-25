package com.jbnucpu.www.dto;

import com.jbnucpu.www.entity.StudyEntity;
import com.jbnucpu.www.entity.StudyMemberEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyDTO {

    @NotNull // 클라이언트에서 Null값 보내면 400에러 발생시킴
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String studentNumber; // 작성자

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String grade;

    @NotNull
    @NotEmpty
    private String college;

    @NotNull
    @NotEmpty
    private String department;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    @NotNull
    @NotEmpty
    private String studySubject;

    @NotNull
    @NotEmpty
    private String studyGoal;

    @NotNull
    @NotEmpty
    private String studyMethod; // 스터디 활동 방식

    @NotNull
    @NotEmpty
    private String studyReference; // 스터디 자료

    @NotNull
    @NotEmpty
    private String studyTime;

    @NotNull
    @NotEmpty
    private String studyPlace;

    @NotNull
    @NotEmpty
    private String managerExperience;

    public StudyEntity toEntity(){
        StudyEntity studyEntity = new StudyEntity();

        studyEntity.setTitle(title);
        studyEntity.setStudentNumber(studentNumber);
        studyEntity.setName(name);
        studyEntity.setGrade(grade);
        studyEntity.setCollege(college);
        studyEntity.setDepartment(department);
        studyEntity.setPhoneNumber(phoneNumber);
        studyEntity.setStudySubject(studySubject);
        studyEntity.setStudyGoal(studyGoal);
        studyEntity.setStudyMethod(studyMethod);
        studyEntity.setStudyReference(studyReference);
        studyEntity.setStudyTime(studyTime);
        studyEntity.setStudyPlace(studyPlace);
        studyEntity.setManagerExperience(managerExperience);

        return studyEntity;
    }

}
