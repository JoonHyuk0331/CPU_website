package com.jbnucpu.www.service;

import com.jbnucpu.www.dto.StudyDTO;
import com.jbnucpu.www.entity.StudyEntity;
import com.jbnucpu.www.repository.StudyRepository;
import org.springframework.stereotype.Service;

@Service
public class StudyOpenService {

    private final StudyRepository studyRepository;

    public StudyOpenService(StudyRepository studyRepository){
        this.studyRepository = studyRepository;
    }

    public Boolean processOpenStudy(StudyDTO studyDTO){

        StudyEntity studyEntity = studyDTO.toEntity();

        studyRepository.save(studyEntity);

        return true;
    }
}
