package org.example.states;

import org.example.board.Square;
import org.example.pieces.Piece;

import java.io.Serializable;

public class StunnedState implements PieceState, Serializable {
    private final Piece context;

    private int durationLeft;

    public StunnedState(Piece context, int duration) {
        this.context = context;
        this.durationLeft = duration;
    }

    @Override
    public void changeState(PieceState newState) {
        this.context.setCanMove(true);
        this.context.setCanAttack(true);

        context.changeState(newState);
    }

    @Override
    public void updateDuration() {
        this.durationLeft--;
    }

    @Override
    public int getDuration() {
        return this.durationLeft;
    };

    @Override
    public void applyEffect() {
        if (this.durationLeft > 0) {
            this.context.setCanMove(false);
            this.context.setCanAttack(false);
        }

        this.updateDuration();

        if (this.durationLeft < 0) {
            this.changeState(new HealthyState(context));
        }
    }
}
