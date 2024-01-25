package com.jbnucpu.www.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StudyMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StudyEntity studyEntity;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="USER_ID")
    private UserEntity userEntity;


}
