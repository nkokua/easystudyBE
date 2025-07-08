package com.easystudy.arona.feedback.controller;


import com.easystudy.arona.feedback.model.entity.FeedBackEntity;
import com.easystudy.arona.feedback.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedBackController {

    private FeedBackService feedBackService;

    @Autowired
    private FeedBackController(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @PostMapping
    public ResponseEntity<?> saveFeedBack(){


        return new ResponseEntity<>(200, HttpStatus.CREATED);
    }
}
