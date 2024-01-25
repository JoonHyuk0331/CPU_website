package com.jbnucpu.www.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EnrollEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //studyEntity목록만 단일로 사용하는 경우가 많으니 지연로딩 걸어놓음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studyEntity_id")
    private StudyEntity studyEntity;
    //회원정보수정시 단일로 사용하는 경우 많으니 지연로딩 걸어놓음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userEntity_id")
    private UserEntity userEntity;

    //todo n+1문제 발생 해결
}
