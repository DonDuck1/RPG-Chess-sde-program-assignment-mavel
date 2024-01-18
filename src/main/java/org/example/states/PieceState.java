package org.example.states;

import org.example.board.Square;

public interface PieceState {
    public void changeState(PieceState newState);

    public void updateDuration();

    public int getDuration();

    public void applyEffect();
}
