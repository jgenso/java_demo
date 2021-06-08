package com.challenge.demo.dto;

import com.challenge.demo.model.Question;
import com.challenge.demo.model.QuestionColumn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionColumnDTO {

	private Long id;

	private Long questionId;

	private String columnName;

	private boolean isCorrectColumn;

	private Date createdAt;

	private Date updatedAt;

	public static QuestionColumn transform(final QuestionColumnDTO newQCDto, final Question question) {
		final QuestionColumn newQC = new QuestionColumn();
		newQC.setColumnName(newQCDto.getColumnName());
		newQC.setIsCorrectColumn(newQCDto.getIsCorrectColumn());
		newQC.setQuestion(question);

		return newQC;
	}

	public static QuestionColumnDTO build(final QuestionColumn save) {
		final QuestionColumnDTO newQCDto = new QuestionColumnDTO();

		newQCDto.setId(save.getId());
		newQCDto.setColumnName(save.getColumnName());
		newQCDto.setIsCorrectColumn(save.isCorrectColumn());
		newQCDto.setCreatedAt(save.getCreatedAt());
		newQCDto.setUpdatedAt(save.getUpdatedAt());
		newQCDto.setQuestionId(save.getQuestion().getQuestionId());

		return newQCDto;
	}

	public static List<QuestionColumnDTO> build(final List<QuestionColumn> columns) {
		final List<QuestionColumnDTO> ret = new ArrayList<>();
		for (QuestionColumn qc : columns) {
			ret.add(build(qc));
		}
		return ret;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(final Long questionId) {
		this.questionId = questionId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(final String columnName) {
		this.columnName = columnName;
	}

	public boolean getIsCorrectColumn() {
		return isCorrectColumn;
	}

	public void setIsCorrectColumn(final boolean correctColumn) {
		isCorrectColumn = correctColumn;
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
