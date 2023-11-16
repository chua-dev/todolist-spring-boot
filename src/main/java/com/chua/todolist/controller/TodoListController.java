package com.chua.todolist.controller;


import com.chua.todolist.exception.TodoListNotFoundException;
import com.chua.todolist.model.entity.TodoList;
import com.chua.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todolist")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @GetMapping
    public ResponseEntity<List<TodoList>> getAllTodoLists() {
        return todoListService.getTodoLists();
    }

    @GetMapping("{todoListId}")
    public ResponseEntity<TodoList> getTodoListById(@PathVariable(value = "todoListId") int todoListId) throws TodoListNotFoundException {
        return todoListService.getTodoListById(todoListId);
    }

    @PostMapping
    public ResponseEntity<TodoList> createTodoList(@RequestBody TodoList todoList){
        return todoListService.saveTodoList(todoList);
    }

    @PutMapping("{todoListId}")
    public  ResponseEntity<TodoList> updateTodoList(@PathVariable(value = "todoListId") int todoListId, @RequestBody TodoList todoList) {
        return todoListService.updateTodoList(todoListId, todoList);
    }

    @DeleteMapping("{todoListId}")
    public ResponseEntity<HttpStatus> deleteTodoList(@PathVariable(value = "todoListId") int todoListId) {
        return todoListService.deleteDepartmentById(todoListId);
    }
}
