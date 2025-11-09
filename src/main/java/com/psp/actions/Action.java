package com.psp.actions;

import com.psp.cityEntities.*;

public interface Action {
    void execute();
    boolean validate();
    void undo();
}
