package org.jackie.mytodo.core.parameter;

import lombok.Getter;

@Getter
public class TodoParameter {

    private String content;

    public TodoParameter(String content) {
        this.content = content;
    }
}
