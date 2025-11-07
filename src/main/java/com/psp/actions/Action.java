package com.psp.actions;

import com.psp.cityEntities.*;

public interface Action {
    void execute(City c);
    void validate(City c);
    void undo(City c);
}
