package com.chua.todolist.repository;

import com.chua.todolist.model.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("todoListRepository")
public interface TodoListRepository extends JpaRepository<TodoList, Long> {

}
