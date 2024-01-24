package com.jbnucpu.www.repository;

import com.jbnucpu.www.entity.StudyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyEnrollRepository extends JpaRepository<StudyMemberEntity, Long> {
}
