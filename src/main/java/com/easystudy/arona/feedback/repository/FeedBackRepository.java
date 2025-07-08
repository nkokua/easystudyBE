package com.easystudy.arona.feedback.repository;

import com.easystudy.arona.feedback.model.entity.FeedBackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedBackRepository extends JpaRepository<FeedBackEntity, String> {

}
