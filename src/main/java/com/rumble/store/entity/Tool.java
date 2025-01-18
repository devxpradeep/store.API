package com.rumble.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document
public class Tool extends AbstractAuditingEntity<String> {

    @Field("name")
    private String name;

    @Field("created_by")
    private String createdBy;

    @Field("modified_by")
    private String modifiedBy;

    @Field("is_active")
    private Boolean isActive;

    @Field("bg_img_url")
    private String bgImageUrl;

    @Field("avatar_url")
    private String avatarUrl;

    @Field("description")
    private String description;
}
