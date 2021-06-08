package com.challenge.demo.controller;

import com.challenge.demo.dto.QuestionAnswerDTO;
import com.challenge.demo.dto.QuestionColumnDTO;
import com.challenge.demo.dto.QuestionDTO;
import com.challenge.demo.model.Question;
import com.challenge.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO incomingQuestion) {
		return questionService.create(incomingQuestion)
				.map(question -> {
					return new ResponseEntity<>(question, HttpStatus.CREATED);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping()
	public ResponseEntity<List<QuestionDTO>> getQuestions() {
		return Optional
				.ofNullable(questionService.getQuestions())
				.map(questions -> ResponseEntity.ok(questions))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("sites/{siteId}")
	public ResponseEntity<List<QuestionDTO>> getQuestions(@PathVariable(value = "siteId") Long siteId) {
		return Optional
				.ofNullable(questionService.getQuestionsBySiteId(siteId))
				.map(questions -> ResponseEntity.ok(questions))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}


	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<QuestionDTO> updateQuestion(@RequestBody Question incomingQuestion, @PathVariable(value = "id") Long questionId) {

		return questionService
				.updateQuestion(incomingQuestion, questionId)
				.map(questionDTO -> {
					return new ResponseEntity<>(questionDTO, HttpStatus.OK);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<QuestionDTO> deleteQuestion(@PathVariable(value = "id") Long questionId) {
		return questionService
				.deleteQuestion(questionId)
				.map(question -> {
					return ResponseEntity.ok(question);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}")
	public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable(value = "id") Long questionId) {
		return questionService
				.getQuestionById(questionId)
				.map(question -> ResponseEntity.ok(question))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/{id}/answers")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<QuestionAnswerDTO> createQuestionAnswers(@PathVariable(value = "id") Long questionId,
																   @RequestBody QuestionAnswerDTO newQADTO) {
		return questionService
				.createQuestionAnswers(questionId, newQADTO)
				.map(question -> {
					return new ResponseEntity<>(question, HttpStatus.CREATED);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}/answers")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<QuestionAnswerDTO>> getQuestionAnswers(@PathVariable(value = "id") Long questionId) {
		return questionService.getQuestionsAnswers(questionId)
				.map(question -> ResponseEntity.ok(question))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/{id}/columns")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<QuestionColumnDTO> createQuestionColumns(@PathVariable(value = "id") Long questionId,
																   @RequestBody QuestionColumnDTO newQCDTO) {
		return questionService
				.createQuestionColumns(questionId, newQCDTO)
				.map(questionColumn -> {
					return new ResponseEntity<>(questionColumn, HttpStatus.CREATED);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}/columns")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<QuestionColumnDTO>> getQuestionColumns(@PathVariable(value = "id") Long questionId) {
		return questionService.getQuestionsColumns(questionId)
				.map(questionColumn -> ResponseEntity.ok(questionColumn))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}