package com.easystudy.arona.feedback.service;


import com.easystudy.arona.feedback.repository.FeedBackRepository;
import com.easystudy.arona.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedBackService {

    private final FeedBackRepository feedBackRepository;

    @Autowired
    public FeedBackService(FeedBackRepository feedBackRepository) {
        this.feedBackRepository = feedBackRepository;
    }



}
