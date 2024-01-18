package org.example.builders;

import org.example.allegiances.Allegiance;
import org.example.allegiances.White;
import org.example.pieces.BishopPiece;
import org.example.states.PieceState;

public class BishopPieceBuilder implements PieceBuilder {
    private BishopPiece bishop = new BishopPiece();

    @Override
    public void reset() {
        this.bishop = new BishopPiece();
    }

    @Override
    public void setAllegiance(Allegiance allegiance) {
        this.bishop.setAllegiance(allegiance);
    }

    @Override
    public void setState(PieceState state) {
        this.bishop.changeState(state);
    }

    @Override
    public void setHealth(double health) {
        this.bishop.setHealth(health);
    }

    @Override
    public void setArmor(int armor) {
        this.bishop.setArmor(armor);
    }

    @Override
    public void setDamage(double damage) {
        this.bishop.setDamage(damage);
    }

    @Override
    public void setCanMove(Boolean canMove) {
        this.bishop.setCanMove(canMove);
    }

    @Override
    public void setCanAttack(Boolean canAttack) {
        this.bishop.setCanAttack(canAttack);
    }

    @Override
    public void setSymbol(String symbol) {
        this.bishop.setSymbol(symbol);
    }

    public BishopPiece getResult() {
        BishopPiece result = this.bishop;
        this.reset();
        return result;
    }
}
