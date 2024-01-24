package com.jbnucpu.www.repository;

import com.jbnucpu.www.entity.StudyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyMemberRepository extends JpaRepository<StudyMemberEntity, Long> {
}
