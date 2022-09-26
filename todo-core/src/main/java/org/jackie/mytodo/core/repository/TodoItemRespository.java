package org.jackie.mytodo.core.repository;

import org.jackie.mytodo.core.model.TodoItem;

import java.util.Iterator;
import java.util.List;

public interface TodoItemRespository {

    TodoItem save(TodoItem todoItem);

    List<TodoItem> findAll();

    /**
     * 使用iterator
     * @return
     */
    Iterable<TodoItem> findAll2();

    TodoItem update(TodoItem todoItem);

    TodoItem findByIndex(TodoItem todoItem);

}
