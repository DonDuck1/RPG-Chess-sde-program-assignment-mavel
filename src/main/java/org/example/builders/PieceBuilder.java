package org.example.builders;

import org.example.allegiances.Allegiance;
import org.example.pieces.Piece;
import org.example.states.PieceState;

public interface PieceBuilder {
    void reset();

    void setAllegiance(Allegiance allegiance);

    void setState(PieceState state);

    void setHealth(double health);

    void setArmor(int armor);

    void setDamage(double damage);

    void setCanMove(Boolean canMove);

    void setCanAttack(Boolean canAttack);

    void setSymbol(String symbol);

    Piece getResult();
}
