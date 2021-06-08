package com.challenge.demo.dto;

import com.challenge.demo.model.QuestionAnswer;
import com.challenge.demo.model.QuestionColumn;
import com.challenge.demo.model.Site;
import com.challenge.demo.model.UserAnswer;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserAnswerDTO {

    private UUID userUUID;

    private UUID siteUUID;

    private Long questionAnswerId;

    private Long questionColumnId;

    private Date createdAt;

    private Date updatedAt;

    public static UserAnswerDTO build(UserAnswer userAnswer) {
        final UserAnswerDTO obj = new UserAnswerDTO();
        obj.setUserUUID(userAnswer.getUserUUID());
        obj.setSiteUUID(userAnswer.getSiteUUID());
        obj.setQuestionAnswerId(userAnswer.getQuestionAnswer().getId());
        obj.setQuestionColumnId(userAnswer.getQuestionColumn().getId());
        obj.setUpdatedAt(userAnswer.getUpdatedAt());
        obj.setCreatedAt(userAnswer.getCreatedAt());

        return obj;
    }

    public static UserAnswer createUserAnswer(final UserAnswerDTO incomingUserAnswer, final QuestionAnswer questionAnswer, final QuestionColumn questionColumn) {
        final UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUserUUID(incomingUserAnswer.getUserUUID());
        userAnswer.setSiteUUID(incomingUserAnswer.getSiteUUID());
        userAnswer.setQuestionAnswer(questionAnswer);
        userAnswer.setQuestionColumn(questionColumn);

        return userAnswer;
    }

    public UUID getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(final UUID userUUID) {
        this.userUUID = userUUID;
    }

    public UUID getSiteUUID() {
        return siteUUID;
    }

    public void setSiteUUID(final UUID siteUUID) {
        this.siteUUID = siteUUID;
    }

    public Long getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(final Long questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    public Long getQuestionColumnId() {
        return questionColumnId;
    }

    public void setQuestionColumnId(final Long questionColumnId) {
        this.questionColumnId = questionColumnId;
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
}
