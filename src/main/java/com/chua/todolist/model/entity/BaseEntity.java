package com.chua.todolist.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "isDeleted", nullable = true, updatable = true)
    private boolean isDeleted;
}
