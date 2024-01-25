package com.jbnucpu.www.service;

import com.jbnucpu.www.dto.StudyDTO;
import com.jbnucpu.www.entity.StudyEntity;
import com.jbnucpu.www.entity.StudyMemberEntity;
import com.jbnucpu.www.entity.UserEntity;
import com.jbnucpu.www.repository.StudyMemberRepository;
import com.jbnucpu.www.repository.StudyRepository;
import com.jbnucpu.www.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudyService {
    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    public StudyService(StudyRepository studyRepository,StudyMemberRepository studyMemberRepository,AuthService authService,UserRepository userRepository){
        this.studyRepository = studyRepository;
        this.studyMemberRepository=studyMemberRepository;
        this.authService=authService;
        this.userRepository=userRepository;
    }

    public List<StudyEntity> getList(){
        List<StudyEntity> studyEntities=this.studyRepository.findAll();
        return studyEntities;
    }

    public Optional<StudyEntity> findStudy(Long no){
        return studyRepository.findById(no);
    }

    public Set<StudyMemberEntity> findStudyMember(Long no){
        Optional<StudyEntity> studyEntity=studyRepository.findById(no);
        Set<StudyMemberEntity> studyMemberEntities=studyEntity.get().getStudyMemberEntities();
        return studyMemberEntities;
    }

    public void deleteStudy(Long no){
        Optional<StudyEntity> studyEntity= studyRepository.findById(no);
        this.studyRepository.delete(studyEntity.get());
    }

    public void saveStudy(StudyDTO studyDTO){
        StudyEntity studyEntity = studyDTO.toEntity();
        studyRepository.save(studyEntity);
    }

    public void editStudy(StudyDTO studyDTO,Long no){
        Optional<StudyEntity> studyEntity= studyRepository.findById(no);
        StudyEntity s=studyEntity.get();
        s.setTitle(studyDTO.getTitle());
        s.setStudentNumber(studyDTO.getStudentNumber());
        s.setName(studyDTO.getName());
        s.setGrade(studyDTO.getGrade());
        s.setCollege(studyDTO.getCollege());
        s.setDepartment(studyDTO.getDepartment());
        s.setPhoneNumber(studyDTO.getPhoneNumber());
        s.setStudySubject(studyDTO.getStudySubject());
        s.setStudyGoal(studyDTO.getStudyGoal());
        s.setStudyMethod(studyDTO.getStudyMethod());
        s.setStudyReference(studyDTO.getStudyReference());
        s.setStudyTime(studyDTO.getStudyTime());
        s.setStudyPlace(studyDTO.getStudyPlace());
        s.setManagerExperience(studyDTO.getManagerExperience());
        studyRepository.save(s);
    }

    //스터디 가입 신청
    public Boolean applyStudy(Long no){
        //새로운 엔티티 생성
        StudyMemberEntity studyMemberEntity = new StudyMemberEntity();

        //StudyMemberEntity에 userEntity 맵핑
        UserEntity userEntity = userRepository.findByStudentnumber(authService.getUsername());
        studyMemberEntity.setUserEntity(userEntity);
        //StudyMemberEntity에 studyEntity 맵핑
        Optional<StudyEntity> studyEntity= studyRepository.findById(no);
        studyMemberEntity.setStudyEntity(studyEntity.get());
        studyMemberRepository.save(studyMemberEntity);
        //UserEnitity에 StudyMemberEntity 맵핑
        userEntity.setStudyMemberEntity(studyMemberEntity);

        //저장
        return true;
    }
}
