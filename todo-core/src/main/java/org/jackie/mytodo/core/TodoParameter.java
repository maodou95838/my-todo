package org.jackie.mytodo.core;

import lombok.Getter;

@Getter
public class TodoParameter {

    private String content;

    public TodoParameter(String content) {
        this.content = content;
    }
}
