package com.challenge.demo.controller;

import com.challenge.demo.dto.FullQuestionDTO;
import com.challenge.demo.dto.SiteDTO;
import com.challenge.demo.dto.UserAnswerDTO;
import com.challenge.demo.model.UserAnswer;
import com.challenge.demo.service.SiteService;
import com.challenge.demo.service.UserAnswerService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/answers")
public class UserAnswerController {

    @Autowired
    private UserAnswerService userAnswerService;

    @PostMapping("/sites/{siteId}/users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<UserAnswerDTO> createUserAnswer(@RequestBody UserAnswerDTO incomingUserAnswer) {
        return userAnswerService.create(incomingUserAnswer)
                .map(question -> {
                    return new ResponseEntity<>(question, HttpStatus.CREATED);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/sites/{siteUUID}/users/{userUUID}")
    public ResponseEntity<FullQuestionDTO> getQuestionForUser(@PathVariable(value = "siteId") UUID siteUUID, @PathVariable(value = "userUUID") UUID userUUID) {
        return userAnswerService.getQuestionForUser(siteUUID, userUUID).map(question -> ResponseEntity.ok(question))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
