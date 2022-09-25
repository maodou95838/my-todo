package org.jackie.mytodo.core;

import java.util.List;

public interface TodoItemRespository {

    TodoItem save(TodoItem todoItem);

    List<TodoItem> findAll();

}
