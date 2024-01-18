package org.example.states;

import org.example.board.Square;
import org.example.pieces.Piece;

import java.io.Serializable;

public class HealthyState implements PieceState, Serializable {
    private final Piece context;

    public HealthyState(Piece context) {
        this.context = context;
    }

    @Override
    public void changeState(PieceState newState) {
        this.context.changeState(newState);
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