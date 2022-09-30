package org.jackie.mytodo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.jackie.mytodo.core.exception.MyTodoException;
import org.jackie.mytodo.core.model.TodoItem;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Jsons {

    private static final TypeFactory FACTORY = TypeFactory.defaultInstance();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static List<TodoItem> toObjects(final File file) {
        final CollectionType type = FACTORY.constructCollectionType(List.class, TodoItem.class);
        try {
            return MAPPER.readValue(file, type);
        } catch (IOException e) {
            throw new MyTodoException("Fail to read objects", e);
        }
    }

    public static void writeObjects(final File file, final Iterable<TodoItem> objects) {
        try {
            MAPPER.writeValue(file, objects);
        } catch (IOException e) {
            throw new MyTodoException("Fail to write objects", e);
        }
    }


    private Jsons() {
    }
}
