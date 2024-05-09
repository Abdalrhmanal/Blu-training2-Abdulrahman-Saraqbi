package com.blulogxi.taskfrist.entities
        ;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

// هنا نقوم بضبط الخصائص الثايتة و التي قد تكون مشتركة مع اكثر من كلاس في المستقبل

@MappedSuperclass
public class BaseEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@CreationTimestamp
@Column(name = "created_at",updatable = false,nullable = false)
    private Instant createdAt;

    @CreationTimestamp
    @Column(name = "updated_at",nullable = true)
    private Instant updatedAt;

    @Version
    private Integer version;
}
