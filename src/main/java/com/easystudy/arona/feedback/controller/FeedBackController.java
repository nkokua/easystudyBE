package com.easystudy.arona.feedback.controller;


import com.easystudy.arona.feedback.dto.RequestFeedBackDto;
import com.easystudy.arona.feedback.model.entity.FeedBackEntity;
import com.easystudy.arona.feedback.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipal;

@RestController
public class FeedBackController {

    private FeedBackService feedBackService;

    @Autowired
    private FeedBackController(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @PostMapping
    public ResponseEntity<?> saveFeedBack(UserPrincipal user, @RequestBody RequestFeedBackDto dto) {
        if(!feedBackService.saveFeedBack(user,dto)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        };
        return new ResponseEntity<>(200, HttpStatus.CREATED);
    }
}
