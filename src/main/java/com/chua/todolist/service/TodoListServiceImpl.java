package com.chua.todolist.service;

import com.chua.todolist.exception.TodoListNotFoundException;
import com.chua.todolist.model.entity.TodoList;
import com.chua.todolist.model.enums.TodoItemStatus;
import com.chua.todolist.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListServiceImpl implements TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    @Override
    public ResponseEntity<TodoList> saveTodoList(TodoList todoList) {
        //todoList.setStatus(TodoItemStatus.NEW);
        System.out.println(todoList);
        return new ResponseEntity<>(todoListRepository.save(todoList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TodoList> getTodoListById(int todoListId) throws TodoListNotFoundException {
        Optional<TodoList> todoList = todoListRepository.findById((long) todoListId);

        if (todoList.isPresent()) {
            return ResponseEntity.ok(todoList.get());
        }

        throw new TodoListNotFoundException("Todo List Not Found");
    }

    @Override
    public ResponseEntity<List<TodoList>> getTodoLists() {
        return new ResponseEntity<>(todoListRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TodoList> updateTodoList(int todoListId, TodoList todoList) {
        Optional<TodoList> todoListDb = todoListRepository.findById((long) todoListId);
        if (todoListDb.isPresent()){
            TodoList todoList1 = todoListDb.get();
            todoList1.setTitle(todoList.getTitle());
            todoList1.setSummary(todoList.getSummary());
            todoList1.setStatus(todoList.getStatus());
            todoList1.setDeleted(todoList.isDeleted());
            return new ResponseEntity<>(todoListRepository.save(todoList1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteDepartmentById(int todoListId) {
        try{
            todoListRepository.deleteById((long) todoListId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
