package org.jackie.mytodo.core.service;

import com.google.common.collect.ImmutableList;
import org.jackie.mytodo.core.model.TodoItem;
import org.jackie.mytodo.core.parameter.TodoIndexParameter;
import org.jackie.mytodo.core.parameter.TodoParameter;
import org.jackie.mytodo.core.repository.TodoItemRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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
        TodoItem foo = new TodoItem("foo");
        foo.assignIndex(1);

        when(respository.findAll()).thenReturn(ImmutableList.of(foo));
        when(respository.save(any())).then(returnsFirstArg());

        final Optional<TodoItem> todoItem = service.markTodoItemDone(TodoIndexParameter.of(1));
        assertThat(todoItem).isPresent();
        final TodoItem actual = todoItem.get();
        assertThat(actual.isDone()).isTrue();
    }


    @Test
    public void should_throw_exception_for_index_is_smaller_than_zero() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.markTodoItemDone(TodoIndexParameter.of(-3)));

    }

    @Test
    public void should_list_all() {
        TodoItem foo = new TodoItem("foo");
        foo.markDone();
        foo.assignIndex(1);

        TodoItem bar = new TodoItem("bar");
        bar.assignIndex(3);

        List<TodoItem> all = ImmutableList.of(foo, bar);
        when(respository.findAll()).thenReturn(all);
        List<TodoItem> allTodoItem = service.list(true);

        assertThat(allTodoItem.size()).isEqualTo(2);
        assertThat(all.get(0).getContent()).isEqualTo("foo");
    }

    @Test
    public void should_list_empty() {
        List<TodoItem> todoItems = ImmutableList.of();
        when(respository.findAll()).thenReturn(todoItems);

        List<TodoItem> result = service.list(true);
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void should_list_all_not_done_item() {
        TodoItem foo = new TodoItem("foo");
        foo.markDone();

        TodoItem bar = new TodoItem("bar");
        List<TodoItem> all = ImmutableList.of(foo, bar);

        when(respository.findAll()).thenReturn(all);
        List<TodoItem> list = service.list(false);
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getContent()).isEqualTo("foo");

    }
}
