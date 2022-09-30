package org.jackie.mytodo.cli.repository;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.jackie.mytodo.core.model.TodoItem;
import org.jackie.mytodo.core.repository.TodoItemRespository;
import org.jackie.mytodo.util.Jsons;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FileTotoItemRepository implements TodoItemRespository {

    @Override
    public TodoItem save(TodoItem todoItem) {
        if (todoItem == null) {
            throw new IllegalArgumentException("Todo item should not be null");
        }

        final Iterable<TodoItem> items = findAll();
        final Iterable<TodoItem> newContent = toSaveContent(todoItem, items);
        Jsons.writeObjects(this.file, newContent);
        return todoItem;
    }

    private Iterable<TodoItem> toSaveContent(final TodoItem newItem, final Iterable<TodoItem> items) {
        if (newItem.getIndex() == 0) {
            int newIndex = Iterables.size(items) + 1;
            newItem.assignIndex(newIndex);

            return ImmutableList.<TodoItem>builder()
                    .addAll(items)
                    .add(newItem)
                    .build();
        }

        return StreamSupport.stream(items.spliterator(), false)
                .map(item -> updateItem(newItem, item))
                .collect(Collectors.toList());
    }

    private TodoItem updateItem(final TodoItem newItem, final TodoItem item) {
        if (item.getIndex() == newItem.getIndex()) {
            return newItem;
        }

        return item;
    }

    @Override
    public List<TodoItem> findAll() {
        if (this.file.length() == 0) {
            return ImmutableList.of();
        }

        return Jsons.toObjects(this.file);
    }



    public FileTotoItemRepository(File file) {
        this.file = file;
    }

    private File file;
}
