package com.easystudy.arona.feedback.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;

import java.util.UUID;
/*

@Entity
@Table
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class FeedBackEntity {
    @Id
    @Column(nullable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();


    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private FeedbackData data;
}
*/
