package com.chua.todolist.model.entity;

import com.chua.todolist.model.enums.TodoItemStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Todolist")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TodoList extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "status")
    private TodoItemStatus status;

}
