package com.challenge.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "question_column")
@EntityListeners(AuditingEntityListener.class)
public class QuestionColumn implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_column_id")
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Question question;

    @Column(name = "column_name")
    private String columnName;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isCorrectColumn;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public QuestionColumn() {
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(final Question question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(final String columnName) {
        this.columnName = columnName;
    }

    public boolean isCorrectColumn() {
        return isCorrectColumn;
    }

    public void setIsCorrectColumn(boolean isCorrectColumn) {
        this.isCorrectColumn = isCorrectColumn;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Date getCreatedAt() {
        return createdAt;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final QuestionColumn that = (QuestionColumn) o;
        return isCorrectColumn == that.isCorrectColumn &&
                Objects.equals(id, that.id) &&
                Objects.equals(question, that.question) &&
                Objects.equals(columnName, that.columnName) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, columnName, isCorrectColumn, createdAt, updatedAt);
    }
}
