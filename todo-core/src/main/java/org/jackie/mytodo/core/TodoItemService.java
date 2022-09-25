package org.jackie.mytodo.core;

import java.util.Optional;

public class TodoItemService {

    private TodoItemRespository respository;

    public TodoItemService(TodoItemRespository respository) {
        this.respository = respository;
    }

    public TodoItem addTodoItem(TodoParameter parameter) {
        if (parameter == null) {
            throw new IllegalArgumentException("parameter is null!");
        }

        TodoItem item = new TodoItem(parameter.getContent());
        return respository.save(item);
    }


    public Optional<TodoItem> markTodoItemDone(TodoIndexParameter index) {
        return Optional.empty();
    }

}
