package com.chua.todolist;

import com.chua.todolist.model.entity.TodoList;
import com.chua.todolist.model.enums.TodoItemStatus;
import com.chua.todolist.repository.TodoListRepository;
import com.chua.todolist.service.TodoListService;
import com.chua.todolist.service.TodoListServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class TodoListRepositoryTests {

    private TodoList todoList1;
    private TodoList todoList2;

    @Autowired
    private TodoListRepository todoListRepository;

    @BeforeEach
    public void init(){

        todoList1 = TodoList.builder()
                .title("First Title")
                .summary("First Summary")
                .status(TodoItemStatus.NEW)
                .build();

        todoList2 = TodoList.builder()
                .title("Second Title")
                .summary("Second Summary")
                .status(TodoItemStatus.NEW)
                .build();

        todoListRepository.saveAll(Arrays.asList(todoList1, todoList2));
    }

    @AfterEach
    public void emptyRecord(){
        todoListRepository.deleteAll();
    }

    @Test
    public void getAllTodoListTest(){
        List<TodoList> result = todoListRepository.findAll();
        Assertions.assertEquals(result.size(), 2);
    }

    @Test
    public void getTodoListById(){
        Optional<TodoList> result = todoListRepository.findById(todoList1.getId());
        TodoList endResult = result.get();
        Assertions.assertEquals("First Title", endResult.getTitle());
        Assertions.assertEquals("First Summary", endResult.getSummary());
        Assertions.assertEquals(TodoItemStatus.NEW, endResult.getStatus());
    }

    @Test
    public void saveTodoListTest(){

        TodoList todoList3 = TodoList.builder()
                .title("Third Title")
                .summary("Third Summary")
                .status(TodoItemStatus.COMPLETE)
                .build();

        TodoList result = todoListRepository.save(todoList3);
        Assertions.assertEquals("Third Title", result.getTitle());
        Assertions.assertEquals("Third Summary", result.getSummary());
        Assertions.assertEquals(TodoItemStatus.COMPLETE, result.getStatus());
    }

    @Test
    public void deleteTodoList(){

        Optional<TodoList> result = todoListRepository.findById(todoList1.getId());

        todoListRepository.delete(result.get());

        Optional<TodoList> todoListCheck = todoListRepository.findById(todoList1.getId());;

        Assertions.assertEquals(todoListCheck, Optional.empty());

    }

}
