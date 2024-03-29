package org.example.builders;

import org.example.allegiances.Allegiance;
import org.example.pieces.KnightPiece;
import org.example.states.PieceState;

public class KnightPieceBuilder implements PieceBuilder {
    private KnightPiece knight = new KnightPiece();

    @Override
    public void reset() {
        this.knight = new KnightPiece();
    }

    @Override
    public void setAllegiance(Allegiance allegiance) {
        this.knight.setAllegiance(allegiance);
    }

    @Override
    public void setState(PieceState state) {
        this.knight.changeState(state);
    }

    @Override
    public void setHealth(double health) {
        this.knight.setHealth(health);
    }

    @Override
    public void setArmor(int armor) {
        this.knight.setArmor(armor);
    }

    @Override
    public void setDamage(double damage) {
        this.knight.setDamage(damage);
    }

    @Override
    public void setCanMove(Boolean canMove) {
        this.knight.setCanMove(canMove);
    }

    @Override
    public void setCanAttack(Boolean canAttack) {
        this.knight.setCanAttack(canAttack);
    }

    @Override
    public void setSymbol(String symbol) {
        this.knight.setSymbol(symbol);
    }

    public KnightPiece getResult() {
        KnightPiece result = this.knight;
        this.reset();
        return result;
    }
}
