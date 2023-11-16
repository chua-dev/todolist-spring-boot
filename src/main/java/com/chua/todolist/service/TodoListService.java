package com.chua.todolist.service;


import com.chua.todolist.exception.TodoListNotFoundException;
import com.chua.todolist.model.entity.TodoList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TodoListService{

    ResponseEntity<TodoList> saveTodoList(TodoList todoList);
    ResponseEntity<TodoList> getTodoListById(int todoListId) throws TodoListNotFoundException;
    ResponseEntity<List<TodoList>> getTodoLists();
    ResponseEntity<TodoList> updateTodoList(int todoListId, TodoList todoList);
    ResponseEntity<HttpStatus> deleteDepartmentById(int todoListId);
}
