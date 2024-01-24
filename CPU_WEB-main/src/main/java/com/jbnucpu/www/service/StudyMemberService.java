package com.jbnucpu.www.service;

import com.jbnucpu.www.repository.StudyMemberRepository;

public class StudyMemberService {

    private final StudyMemberRepository studyMemberRepository;

    public StudyMemberService(StudyMemberRepository studyMemberRepository){
        this.studyMemberRepository=studyMemberRepository;
    }


}
