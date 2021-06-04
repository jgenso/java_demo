package com.challenge.demo.service;

import com.challenge.demo.dto.QuestionAnswerDTO;
import com.challenge.demo.dto.QuestionDTO;
import com.challenge.demo.model.Question;
import com.challenge.demo.model.QuestionAnswer;
import com.challenge.demo.repository.QuestionAnswerRepository;
import com.challenge.demo.repository.QuestionRepository;
import com.challenge.demo.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Override
    public Optional<QuestionDTO> create(QuestionDTO questionDTO) {
        return siteRepository
                .findById(questionDTO.getSiteId())
                .map(site -> {
                    final Question newQ = QuestionDTO.createQuestion(questionDTO, site);
                    return QuestionDTO.build(questionRepository.save(newQ));
                });
    }

    @Override
    public List<QuestionDTO> getQuestions() {
        return QuestionDTO.build(questionRepository.findAll());
    }

    public List<QuestionDTO> getQuestionsBySiteId(Long siteId) {
        return QuestionDTO.build(questionRepository.findSiteQuestions(siteId));
    }

    public Optional<QuestionDTO> updateQuestion(Question question, Long questionId) {
        return questionRepository
                .findById(questionId)
                .map(q -> {
                    q.setQuestion(question.getQuestion());
                    q.setSite(question.getSite());
                    return QuestionDTO.build(questionRepository.save(question));
                });
    }

    public Optional<QuestionDTO> deleteQuestion(Long questionId) {
        return questionRepository
                .findById(questionId)
                .map(question -> {
                    questionRepository.delete(question);
                    return QuestionDTO.build(question);
                });
    }

    public Optional<QuestionDTO> getQuestionById(Long questionId) {
        return questionRepository
                .findById(questionId)
                .map(question -> QuestionDTO.build(question));
    }

    public Optional<QuestionAnswerDTO> createQuestionAnswers(Long questionId, QuestionAnswerDTO questionAnswerDTO) {
        return questionRepository
                .findById(questionId)
                .map(question -> {
                    final QuestionAnswer newQa = QuestionAnswerDTO.transform(questionAnswerDTO, question);
                    return QuestionAnswerDTO.build(questionAnswerRepository.save(newQa));
                });
    }

    public Optional<List<QuestionAnswerDTO>> getQuestionsAnswers(Long questionId) {
        return questionRepository
                .findById(questionId)
                .map(question -> QuestionAnswerDTO.build(question.getAnswers()));
    }
}
