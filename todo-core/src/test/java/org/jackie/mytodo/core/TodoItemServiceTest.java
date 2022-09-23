package org.jackie.mytodo.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TodoItemServiceTest {

    @Test
    public void should_add_todo_item() {
        TodoItemRespository respository = mock(TodoItemRespository.class);

        when(respository.save(any())).then(returnsFirstArg());

        TodoItemService service = new TodoItemService(respository);
        TodoParameter item = service.addTodoItem(new TodoParameter("foo"));
        assertThat(item.getContent()).isEqualTo("foo");
        System.out.println("hahaha");
    }

    @Test
    public void should_throw_exception_for_null_todo_item() {
        TodoItemRespository respository = mock(TodoItemRespository.class);
        when(respository.save(any())).then(returnsFirstArg());
        TodoItemService service = new TodoItemService(respository);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.addTodoItem(null));
    }
}
