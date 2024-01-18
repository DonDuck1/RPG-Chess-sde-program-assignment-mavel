package org.example.allegiances;

import org.example.board.Square;

import java.io.Serializable;

public class Black implements Allegiance, Serializable {
    public void movePawn(Square currentSquare, Square squareToMoveTo, Square[][] squares) {
        if (
            squareToMoveTo.getX() - currentSquare.getX() == 0 &&
            squareToMoveTo.getY() - currentSquare.getY() == -1 &&
            squareToMoveTo.getPiece() == null
        ) {
            squareToMoveTo.setPiece(currentSquare.getPiece());
            currentSquare.setPiece(null);
        }
    };

    public void attackWithPawn(Square currentSquare, Square squareToAttack, Square[][] squares) {
        if (
            ((squareToAttack.getX() - currentSquare.getX() == -1 &&
                squareToAttack.getY() - currentSquare.getY() == -1) ||
            (squareToAttack.getX() - currentSquare.getX() == 1 &&
                squareToAttack.getY() - currentSquare.getY() == -1)) &&
            squareToAttack.getPiece() != null &&
            squareToAttack.getPiece().getAllegiance() != currentSquare.getPiece().getAllegiance()
        ) {
            squareToAttack.getPiece().takeDamage(currentSquare.getPiece().getDamage());
        }
    };
}
