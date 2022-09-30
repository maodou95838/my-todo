package org.jackie.mytodo.core.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoItem {

    private String content;

    private boolean done;

    private int index;

    public TodoItem(final String content) {
        this.content = content;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void assignIndex(int index) {
        this.index = index;
    }
}
