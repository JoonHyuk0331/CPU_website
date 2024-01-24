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
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String studentnumber;
    private String password;

    private String college; // 단과대학
    private String department; // 학과
    private Boolean isregistered; // 재학 상태 (True => 재학, False => 휴학)
    private String grade;

    //@Column(unique = true)
    private String phonenumber;

    @CreationTimestamp
    private Timestamp registerdate; // 계정 생성 일자
    @UpdateTimestamp
    private Timestamp updateregisterdate; // 계정 수정 일자

    private String enrolldate; // 회비 납부 확인 후 수락 일자

    //@Column(unique = true)
    private String nickname;
    private String role;

    //@Column(unique = true)
    private String judgementkey; // 혹시 쓰일지 모르는 개인 key 값

    @OneToOne
    private StudyMemberEntity studyMemberEntity;
}
