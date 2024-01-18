package org.example.states;

import java.io.Serializable;

public class NullState implements PieceState, Serializable {
    @Override
    public void changeState(PieceState newState) {

    }

    @Override
    public void updateDuration() {

    }

    @Override
    public int getDuration() {
        return 99;
    };

    @Override
    public void applyEffect() {

    }
}
