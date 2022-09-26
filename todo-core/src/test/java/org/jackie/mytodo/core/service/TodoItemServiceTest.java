package org.jackie.mytodo.core.service;

import com.google.common.collect.ImmutableList;
import org.jackie.mytodo.core.model.TodoItem;
import org.jackie.mytodo.core.parameter.TodoIndexParameter;
import org.jackie.mytodo.core.parameter.TodoParameter;
import org.jackie.mytodo.core.repository.TodoItemRespository;
import org.jackie.mytodo.core.service.TodoItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
        when(respository.save(any())).then(returnsFirstArg());
        TodoItem item = service.addTodoItem(new TodoParameter("foo"));
        assertThat(item.getContent()).isEqualTo("foo");
    }

    @Test
    public void should_throw_exception_for_null_todo_item() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.addTodoItem(null));
    }

    @Test
    public void should_mark_todo_item_as_done() {
        when(respository.findAll()).thenReturn(ImmutableList.of(new TodoItem("foo")));
        when(respository.save(any())).then(returnsFirstArg());

        final Optional<TodoItem> todoItem = service.markTodoItemDone(TodoIndexParameter.of(1));
        assertThat(todoItem).isPresent();
        final TodoItem actual = todoItem.get();
        assertThat(actual.isDone()).isTrue();
    }
}
