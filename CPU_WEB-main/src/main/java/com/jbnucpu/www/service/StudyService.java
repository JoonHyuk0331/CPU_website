package com.jbnucpu.www.service;

import com.jbnucpu.www.dto.StudyDTO;
import com.jbnucpu.www.entity.StudyEntity;
import com.jbnucpu.www.entity.EnrollEntity;
import com.jbnucpu.www.entity.UserEntity;
import com.jbnucpu.www.repository.StudyEnrollRepository;
import com.jbnucpu.www.repository.StudyRepository;
import com.jbnucpu.www.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudyService {
    private final StudyRepository studyRepository;
    private final StudyEnrollRepository studyEnrollRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    public StudyService(StudyRepository studyRepository,StudyEnrollRepository studyEnrollRepository,AuthService authService,UserRepository userRepository){
        this.studyRepository = studyRepository;
        this.studyEnrollRepository=studyEnrollRepository;
        this.authService=authService;
        this.userRepository=userRepository;
    }

    public List<StudyEntity> getList(){
        List<StudyEntity> studyEntities=this.studyRepository.findAll();
        return studyEntities;
    }

    public Boolean processOpenStudy(StudyDTO studyDTO){
        StudyEntity studyEntity = studyDTO.toEntity();
        studyRepository.save(studyEntity);
        return true;
    }

    public Optional<StudyEntity> findStudy(Long no){
        return studyRepository.findById(no);
    }

    //no에 해당하는 StudyEntity의 enrollEntity들을 반환
    public Set<EnrollEntity> findStudyMember(Long no){
        Optional<StudyEntity> studyEntity=studyRepository.findById(no);
        Set<EnrollEntity> studyMemberEntities=studyEntity.get().getEnrollEntities_Study();
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

    //스터디 가입 신청(로그인 되어 authService.getUsername() 작동하는 상태여야 함)
    public Boolean applyStudy(Long no){
        //todo 로그인 안된 상태면 작동 안하게
        /*방식: authService에 로그인여부 판단하는 isAuthenticated메소드 정의해놓음*/
        if(authService.isAuthenticated()==false){
            System.out.println("신청실패: 로그인 안 한 상태로 신청");
            return false;
        }
        //todo 중복신청방지
        /*방식: 현재 로그인된 유저의 학번에 해당하는 userEntity의id가 EnrollEntity의 UserEntity 컬럼에 존재하는지 확인해서
        있으면(isPresent) 중복신청으로 판단*/
        UserEntity userEntity_AvoidDuplicate = userRepository.findByStudentnumber(authService.getUsername());
        Optional<EnrollEntity> userOptional = studyEnrollRepository.findByUserEntityId(userEntity_AvoidDuplicate.getId());
        if(userOptional.isPresent()){
            System.out.println("신청실패: 이미 신청된 사용자");
            return false;
        }

        //새로운 엔티티 생성
        EnrollEntity enrollEntity = new EnrollEntity();

        //enrollEntityEntity에 userEntity 맵핑
        UserEntity userEntity = userRepository.findByStudentnumber(authService.getUsername());//주의 getUsername()해당메소드 학번반환함 이름 반환안함
        enrollEntity.setUserEntity(userEntity);
        //enrollEntityEntity에 studyEntity 맵핑
        Optional<StudyEntity> studyEntity= studyRepository.findById(no);
        enrollEntity.setStudyEntity(studyEntity.get());
        studyEnrollRepository.save(enrollEntity);
        //UserEnitity에 StudyMemberEntity 맵핑
        /*User는 여러개의 enrollEntity를 가질 수 있어서 add로 추가함, 이게 정석인진 모르겠음*/
        Set<EnrollEntity> tempEnrollEntities_User=userEntity.getEnrollEntities_User();
        tempEnrollEntities_User.add(enrollEntity);
        userEntity.setEnrollEntities_User(tempEnrollEntities_User);
        userRepository.save(userEntity);

        //저장
        return true;
    }

    //스터디 탈퇴
    //현재 유저가 enrollEntity에 있는지 찾아서 해당 enrollEntity삭제
    public boolean quitStudy(Long no){
        if(authService.isAuthenticated()==false){
            System.out.println("탈퇴실패: 로그인 안 한 상태로 탈퇴");
            return false;
        }
        UserEntity userEntity = userRepository.findByStudentnumber(authService.getUsername());
        Optional<EnrollEntity> userOptional = studyEnrollRepository.findByUserEntityId(userEntity.getId());

        if(userOptional.isPresent()==false){
            System.out.println("삭제실패: 삭제할 사용자를 찾을 수 없음");
            return false;
        }
        this.studyEnrollRepository.delete(userOptional.get());

        return true;
    }
}
