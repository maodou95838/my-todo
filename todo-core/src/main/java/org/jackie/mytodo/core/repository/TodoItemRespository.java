package org.jackie.mytodo.core.repository;

import org.jackie.mytodo.core.model.TodoItem;

import java.util.Iterator;
import java.util.List;

public interface TodoItemRespository {

    /**
     * save
     * @param todoItem
     * @return
     */
    TodoItem save(TodoItem todoItem);

    /**
     * findAll
     * @return
     */
    List<TodoItem> findAll();


}
