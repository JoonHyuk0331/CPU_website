package com.jbnucpu.www.dto;

import com.jbnucpu.www.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

@Getter
@Setter
public class SignUpDTO {

    private String name;

    private String studentnumber;
    private String password;

    private String college; // 단과대학
    private String department; // 학과
    private Boolean isregistered = false; // 재학 상태 (True => 재학, False => 휴학)
    private String grade;

    private String phonenumber;

    public UserEntity toEntity() {

        UserEntity userEntity = new UserEntity();

        userEntity.setName(name);
        userEntity.setStudentnumber(studentnumber);
        userEntity.setPassword(password);
        userEntity.setCollege(college);
        userEntity.setDepartment(department);
        userEntity.setIsregistered(isregistered);
        userEntity.setGrade(grade);
        userEntity.setPhonenumber(phonenumber);

        userEntity.setRole("ROLE_USER");
        userEntity.setNickname(studentnumber);
        userEntity.setEnrolldate("2024-00-00");
        userEntity.setJudgementkey("aaaaaaaaaaaaaaaaaaa");

        return userEntity;
    }
}
