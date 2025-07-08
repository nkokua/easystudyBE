package com.easystudy.arona.feedback.model.entity;


import com.easystudy.arona.user.model.entity.UserEntity;
import jakarta.persistence.*;


import java.sql.Timestamp;
import java.util.UUID;


@Entity
@Table
public class FeedBackEntity {
    @Id
    @Column(nullable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private UserEntity user;

    //대답
    @Column(columnDefinition = "jsonb")
    private String data;

    @Column
    private String character;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    private Timestamp timestamp;

    public FeedBackEntity() {}
}

