package org.jackie.mytodo.core.service;

import org.jackie.mytodo.core.model.TodoItem;
import org.jackie.mytodo.core.parameter.TodoIndexParameter;
import org.jackie.mytodo.core.parameter.TodoParameter;
import org.jackie.mytodo.core.repository.TodoItemRespository;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TodoItemService {

    private TodoItemRespository respository;

    public TodoItemService(TodoItemRespository respository) {
        this.respository = respository;
    }

    /**
     * 添加任务
     * @param parameter
     * @return
     */
    public TodoItem addTodoItem(TodoParameter parameter) {
        if (parameter == null) {
            throw new IllegalArgumentException("parameter is null!");
        }

        TodoItem item = new TodoItem(parameter.getContent());
        return respository.save(item);
    }


    /**
     * 标记任务完成
     * @param index
     * @return
     */
    public Optional<TodoItem> markTodoItemDone(TodoIndexParameter index) {
        List<TodoItem> all = respository.findAll();
        Optional<TodoItem> optionalTodoItem =
                all.stream().filter(e -> e.getIndex() == index.getIndex()).findFirst();

        return optionalTodoItem.flatMap(this :: doMarkAsDone);
    }

    private Optional<TodoItem> doMarkAsDone(final TodoItem todoItem) {
        todoItem.markDone();
        return Optional.of(respository.save(todoItem));
    }

    /**
     * 查全部
     * @param all
     * @return
     */
    public List<TodoItem> list(final boolean all) {
        return respository.findAll().stream()
                .filter(e -> (all || e.isDone())).collect(Collectors.toList());
    }

}
