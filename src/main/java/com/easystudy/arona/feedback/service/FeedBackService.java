package com.easystudy.arona.feedback.service;


import com.easystudy.arona.feedback.dto.RequestFeedBackDto;
import com.easystudy.arona.feedback.model.entity.FeedBackEntity;
import com.easystudy.arona.feedback.repository.FeedBackRepository;
import com.easystudy.arona.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FeedBackService {

    private final FeedBackRepository feedBackRepository;

    @Autowired
    public FeedBackService(FeedBackRepository feedBackRepository) {
        this.feedBackRepository = feedBackRepository;
    }

    public boolean saveFeedBack(RequestFeedBackDto dto){
        FeedBackEntity feedBackEntity = new FeedBackEntity();
        dto.getJson();
    }


}
