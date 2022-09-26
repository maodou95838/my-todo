package org.jackie.mytodo.core.parameter;

import lombok.Getter;

@Getter
public class TodoIndexParameter {
    private int index;

    public TodoIndexParameter(int index) {
        this.index = index;
    }

    public static TodoIndexParameter of(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("Todo index should be greater than 0");
        }

        return new TodoIndexParameter(index);
    }
}
