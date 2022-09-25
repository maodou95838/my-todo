package org.jackie.mytodo.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TodoItemServiceTest {
    private TodoItemRespository respository;
    private TodoItemService service;

    @BeforeEach
    public void setUp() {
        this.respository = mock(TodoItemRespository.class);
        this.service = new TodoItemService(respository);
    }


    @Test
    public void should_add_todo_item() {
//        TodoItemRespository respository = mock(TodoItemRespository.class);

        when(respository.save(any())).then(returnsFirstArg());

//        TodoItemService service = new TodoItemService(respository);
        TodoItem item = service.addTodoItem(new TodoParameter("foo"));
        assertThat(item.getContent()).isEqualTo("foo");
        System.out.println("hahaha");
    }

    @Test
    public void should_throw_exception_for_null_todo_item() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.addTodoItem(null));
    }

    @Test
    public void should_mark_todo_item_as_done() {
        when(respository.findAll()).thenReturn()
    }
}
