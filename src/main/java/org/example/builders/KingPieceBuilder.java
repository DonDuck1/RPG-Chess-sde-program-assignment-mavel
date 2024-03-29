package org.example.builders;

import org.example.allegiances.Allegiance;
import org.example.pieces.KingPiece;
import org.example.states.PieceState;

public class KingPieceBuilder implements PieceBuilder {
    private KingPiece king = new KingPiece();

    @Override
    public void reset() {
        this.king = new KingPiece();
    }

    @Override
    public void setAllegiance(Allegiance allegiance) {
        this.king.setAllegiance(allegiance);
    }

    @Override
    public void setState(PieceState state) {
        this.king.changeState(state);
    }

    @Override
    public void setHealth(double health) {
        this.king.setHealth(health);
    }

    @Override
    public void setArmor(int armor) {
        this.king.setArmor(armor);
    }

    @Override
    public void setDamage(double damage) {
        this.king.setDamage(damage);
    }

    @Override
    public void setCanMove(Boolean canMove) {
        this.king.setCanMove(canMove);
    }

    @Override
    public void setCanAttack(Boolean canAttack) {
        this.king.setCanAttack(canAttack);
    }

    @Override
    public void setSymbol(String symbol) {
        this.king.setSymbol(symbol);
    }

    public KingPiece getResult() {
        KingPiece result = this.king;
        this.reset();
        return result;
    }
}
