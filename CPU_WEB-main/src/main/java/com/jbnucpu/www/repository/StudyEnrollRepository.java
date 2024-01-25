package com.jbnucpu.www.repository;

import com.jbnucpu.www.entity.EnrollEntity;
import com.jbnucpu.www.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudyEnrollRepository extends JpaRepository<EnrollEntity, Long> {
    Optional<EnrollEntity> findByUserEntityId(Long userId);
}
