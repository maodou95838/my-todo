package org.jackie.mytodo.core.service;

import org.jackie.mytodo.core.model.TodoItem;
import org.jackie.mytodo.core.parameter.TodoIndexParameter;
import org.jackie.mytodo.core.parameter.TodoParameter;
import org.jackie.mytodo.core.repository.TodoItemRespository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

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
        List<TodoItem> all = respository.findAll();
        Optional<TodoItem> todoItem =
                all.stream().filter(e -> e.getIndex() == index.getIndex()).findFirst();

//        Iterable<TodoItem> iter = respository.findAll2();
//        Optional<TodoItem> todoItem1 = StreamSupport.stream(iter.spliterator(), false)
//                .filter(e -> e.getIndex() == index.getIndex())
//                .findFirst();
        TodoItem test1 = todoItem.get();
        test1.markDone();

//        Optional<TodoItem> test2 = todoItem.map(e -> e.markDone());
//        Optional<TodoItem> test3 = todoItem.flatMap(e -> e.markDone());
//
//
//        Optional<TodoItem> test4 = todoItem.flatMap(e -> e.);

        todoItem.ifPresent(item ->item.markDone());


        return todoItem;
    }

    private Optional<TodoItem> doMarkAsDone(final TodoItem todoItem) {
        todoItem.markDone();
        return Optional.of(respository.save(todoItem));
    }
//
//    public List<TodoItem> list() {
//        return Collections.EMPTY_LIST;
//    }

}
