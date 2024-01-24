package com.jbnucpu.www.service;

import com.jbnucpu.www.entity.StudyMemberEntity;
import com.jbnucpu.www.entity.StudyEntity;
import com.jbnucpu.www.entity.UserEntity;
import com.jbnucpu.www.repository.StudyEnrollRepository;
import com.jbnucpu.www.repository.StudyRepository;
import com.jbnucpu.www.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudyEnrollService {

    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final StudyEnrollRepository studyEnrollRepository;

    private final AuthService authService;

    public StudyEnrollService(StudyRepository studyRepository, UserRepository userRepository, StudyEnrollRepository studyEnrollRepository, AuthService authService){
        this.studyRepository = studyRepository;
        this.userRepository = userRepository;
        this.studyEnrollRepository = studyEnrollRepository;
        this.authService = authService;
    }

    /*public Boolean studyApplyProcess(){

        StudyMemberEntity studyEnrollEntity = new StudyMemberEntity();
        studyEnrollRepository.save(studyEnrollEntity);

        UserEntity userEntity = userRepository.findByStudentnumber(authService.getUsername());

        userEntity.addStudy(studyEnrollEntity);
        userRepository.save(userEntity);

        return true;
    }*/
}
