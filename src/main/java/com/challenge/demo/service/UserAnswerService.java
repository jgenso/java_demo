package com.challenge.demo.service;

import com.challenge.demo.dto.FullQuestionDTO;
import com.challenge.demo.dto.UserAnswerDTO;
import com.challenge.demo.model.UserAnswer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserAnswerService {
    Optional<UserAnswerDTO> create(UserAnswerDTO userAnswerDTO);

    Optional<FullQuestionDTO> getQuestionForUser(UUID siteUUID, UUID userUUID);

}
