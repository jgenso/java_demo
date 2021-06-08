package com.challenge.demo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user_answer")
@EntityListeners(AuditingEntityListener.class)
public class UserAnswer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_answer_id")
    private Long userAnswerId;

    @Column(nullable = false, name = "user_uuid")
    private UUID userUUID;

    @Column(nullable = false, name = "site_uuid")
    private UUID siteUUID;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_answer_id", referencedColumnName = "question_answer_id")
    private QuestionAnswer questionAnswer;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_column_id", referencedColumnName = "question_column_id")
    private QuestionColumn questionColumn;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public UUID getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(UUID userUUID) {
        this.userUUID = userUUID;
    }

    public UUID getSiteUUID() {
        return siteUUID;
    }

    public void setSiteUUID(UUID siteUUID) {
        this.siteUUID = siteUUID;
    }

    public QuestionAnswer getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(final QuestionAnswer questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public QuestionColumn getQuestionColumn() {
        return questionColumn;
    }

    public void setQuestionColumn(final QuestionColumn questionColumn) {
        this.questionColumn = questionColumn;
    }

    public Long getQuestionId() {
        return questionAnswer.getQuestion().getQuestionId();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAnswerId, userUUID, siteUUID, createdAt, updatedAt);
    }
}