package org.jackie.mytodo.core;

import lombok.Getter;

@Getter
public class TodoIndexParameter {
    private int index;

    public TodoIndexParameter(int index) {
        this.index = index;
    }
}
