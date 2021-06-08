package com.challenge.demo.service;

import com.challenge.demo.dto.FullQuestionDTO;
import com.challenge.demo.dto.QuestionDTO;
import com.challenge.demo.dto.SiteDTO;
import com.challenge.demo.dto.UserAnswerDTO;
import com.challenge.demo.model.Question;
import com.challenge.demo.model.Site;
import com.challenge.demo.model.UserAnswer;
import com.challenge.demo.repository.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserAnswerServiceImpl implements UserAnswerService {

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    private QuestionColumnRepository questionColumnRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Override
    public Optional<UserAnswerDTO> create(UserAnswerDTO userAnswerDTO) {
        return questionAnswerRepository
                .findById(userAnswerDTO.getQuestionAnswerId())
                .flatMap(questionAnswer -> {
                    return questionColumnRepository
                            .findById(userAnswerDTO.getQuestionColumnId())
                            .map(questionColumn -> {
                                final UserAnswer newUserAnswer = UserAnswerDTO.createUserAnswer(userAnswerDTO, questionAnswer, questionColumn);
                                return UserAnswerDTO.build(userAnswerRepository.save(newUserAnswer));
                            });
                });
    }

    @Override
    public Optional<FullQuestionDTO> getQuestionForUser(UUID siteUUID, UUID userUUID) {
        return Optional.ofNullable(siteRepository.findByUUID(siteUUID)).flatMap(site -> {
            List<UserAnswer> userAnswerList = userAnswerRepository
                    .findUserAnswersBySiteUUIDAndUserUUID(siteUUID, userUUID);

            List<Long> questionIds = userAnswerList
                    .stream()
                    .map(item -> item.getQuestionId())
                    .collect(Collectors.toList());

            Optional<Question> question = Optional.ofNullable(questionRepository.findByIdAndSiteIdNotIn(site.getSiteId(), questionIds).get(0));

            if (!question.isPresent()) {
                userAnswerRepository.deleteInBatch(userAnswerList);
                question = Optional.ofNullable(questionRepository.findByIdAndSiteIdNotIn(site.getSiteId(), new ArrayList<Long>()).get(0));
            }

            return question.map(q -> {
                return FullQuestionDTO.build(q);
            });
        });
    }

}
