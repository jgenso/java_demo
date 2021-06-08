package com.challenge.demo.dto;

import com.challenge.demo.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FullQuestionDTO {

    private Long questionId;

    private Long siteId;

    private String question;

    private Date createdAt;

    private Date updatedAt;

    private QuestionType questionType;

    private List<QuestionAnswerDTO> questionAnswerDTOList;

    private List<QuestionColumnDTO> questionColumnDTOList;

    public static FullQuestionDTO build(Question question) {
        final FullQuestionDTO obj = new FullQuestionDTO();
        final List<QuestionAnswerDTO> answers = question
                .getAnswers()
                .stream()
                .map(qa -> QuestionAnswerDTO.build(qa))
                .collect(Collectors.toList());
        final List<QuestionColumnDTO> columns = question
                .getColumns()
                .stream()
                .map(qc -> QuestionColumnDTO.build(qc))
                .collect(Collectors.toList());

        obj.setSiteId(question.getSite().getSiteId());
        obj.setQuestionId(question.getQuestionId());
        obj.setQuestion(question.getQuestion());
        obj.setUpdatedAt(question.getUpdatedAt());
        obj.setCreatedAt(question.getCreatedAt());
        obj.setQuestionType(question.getQuestionType());
        obj.setQuestionAnswerDTOList(answers);
        obj.setQuestionColumnDTOList(columns);


        return obj;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(final Long siteId) {
        this.siteId = siteId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(final String question) {
        this.question = question;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(final Long questionId) {
        this.questionId = questionId;
    }

    public QuestionType getQuestionType() { return questionType; }
    public void setQuestionType(QuestionType questionType) { this.questionType = questionType; }

    public List<QuestionAnswerDTO> getQuestionAnswerDTOList() { return questionAnswerDTOList; }

    public void setQuestionAnswerDTOList(List<QuestionAnswerDTO> questionAnswerDTOList) { this.questionAnswerDTOList = questionAnswerDTOList; }

    public List<QuestionColumnDTO> getQuestionColumnDTOList() { return questionColumnDTOList; }

    public void setQuestionColumnDTOList(List<QuestionColumnDTO> questionColumnDTOList) { this.questionColumnDTOList = questionColumnDTOList; }
}
