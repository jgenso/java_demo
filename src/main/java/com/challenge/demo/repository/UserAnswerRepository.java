package com.challenge.demo.repository;

import com.challenge.demo.model.Question;
import com.challenge.demo.model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    @Query(value = "SELECT ua.* FROM user_answer ua WHERE ua.site_uuid = ?1 and ua.user_uuid", nativeQuery = true)
    List<UserAnswer> findUserAnswersBySiteUUIDAndUserUUID(UUID siteUUID, UUID userUUID);
}