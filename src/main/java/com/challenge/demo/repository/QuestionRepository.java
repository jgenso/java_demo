package com.challenge.demo.repository;

import com.challenge.demo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query(value = "SELECT q.* FROM question q WHERE q.site_id = ?1", nativeQuery = true)
	List<Question> findSiteQuestions(Long siteId);

	@Query(value = "SELECT q.* FROM question q WHERE q.site_id = ?1 AND q.question_id = ?2 LIMIT 1", nativeQuery = true)
	List<Question> findByIdAndSiteIdNotIn(Long siteId, List<Long> ids);


}