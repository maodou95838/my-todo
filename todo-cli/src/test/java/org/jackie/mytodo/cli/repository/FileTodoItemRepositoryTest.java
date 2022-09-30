package org.jackie.mytodo.cli.repository;

import org.jackie.mytodo.core.model.TodoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class FileTodoItemRepositoryTest {

    @TempDir
    File tempDir;
    private File tempFile;
    private FileTotoItemRepository repository;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = File.createTempFile("file", "", tempDir);
        repository = new FileTotoItemRepository(this.tempFile);
    }

    @Test
    public void should_find_nothing_for_empty_repository() {
        List<TodoItem> items = repository.findAll();
        assertThat(items).hasSize(0);
    }

    @Test
    public void should_not_save_null_todo_item() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> repository.save(null));
    }

    @Test
    public void should_find_saved_items() {
        repository.save(new TodoItem("foo"));
        repository.save(new TodoItem("bar"));
        final List<TodoItem> items = repository.findAll();
        assertThat(items).hasSize(2);

        final TodoItem firstItem = items.get(0);
        assertThat(firstItem.getContent()).isEqualTo("foo");
        assertThat(firstItem.getIndex()).isEqualTo(1);

    }

}
