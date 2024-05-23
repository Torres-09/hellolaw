package com.hellolaw.hellolaw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hellolaw.hellolaw.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	@Query("select a from Answer a join fetch a.summaryAnswer sa join fetch a.question q where a.question.id = :questionId")
	Optional<Answer> findAnswerByQuestionIdUsingFetchJoin(Long questionId);
}
