package org.jackie.mytodo.core.model;

import lombok.Getter;

@Getter
public class TodoItem {

    private final String content;

    private boolean done;

    private int index;

    public TodoItem(final String content) {
        this.content = content;
    }

    public void markDone() {
        this.done = true;
    }
}
