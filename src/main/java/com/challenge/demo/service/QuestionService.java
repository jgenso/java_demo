package com.challenge.demo.service;

import com.challenge.demo.dto.QuestionAnswerDTO;
import com.challenge.demo.dto.QuestionDTO;
import com.challenge.demo.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Optional<QuestionDTO> create(QuestionDTO questionDto);

    List<QuestionDTO> getQuestions();

    List<QuestionDTO> getQuestionsBySiteId(Long siteId);

    Optional<QuestionDTO> updateQuestion(Question question, Long questionId);

    Optional<QuestionDTO> deleteQuestion(Long questionId);

    Optional<QuestionDTO> getQuestionById(Long questionId);

    Optional<QuestionAnswerDTO> createQuestionAnswers(Long questionId, QuestionAnswerDTO questionAnswerDTO);

    Optional<List<QuestionAnswerDTO>> getQuestionsAnswers(Long questionId);
}
