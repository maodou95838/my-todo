package org.jackie.mytodo.core;

public class TodoItemService {

    private TodoItemRespository respository;

    public TodoItemService(TodoItemRespository respository) {
        this.respository = respository;
    }

    public TodoParameter addTodoItem(TodoParameter parameter) {
        if (parameter == null) {
            throw new IllegalArgumentException("parameter is null!");
        }

        TodoItem item = new TodoItem(parameter.getContent());
        return respository.save(item);
    }
}
