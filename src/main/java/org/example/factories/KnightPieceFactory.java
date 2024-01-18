package org.example.factories;

import org.example.allegiances.Allegiance;
import org.example.builders.KnightPieceBuilder;
import org.example.pieces.KnightPiece;
import org.example.states.HealthyState;
import org.example.states.NullState;

public class KnightPieceFactory implements PieceFactory<KnightPieceBuilder> {
    private final Allegiance whiteAllegiance;

    private final Allegiance blackAllegiance;

    private KnightPieceBuilder builder;

    public KnightPieceFactory(Allegiance whiteAllegiance, Allegiance blackAllegiance) {
        this.whiteAllegiance = whiteAllegiance;
        this.blackAllegiance = blackAllegiance;
        this.builder = new KnightPieceBuilder();
    };

    @Override
    public KnightPiece createWhitePiece() {
        this.builder.setAllegiance(this.whiteAllegiance);
        this.builder.setState(new NullState());
        this.builder.setHealth(3);
        this.builder.setArmor(1);
        this.builder.setDamage(3);
        this.builder.setCanMove(true);
        this.builder.setCanAttack(true);
        this.builder.setSymbol("♘");

        KnightPiece knight = this.builder.getResult();
        knight.changeState(new HealthyState(knight));

        return knight;
    };

    @Override
    public KnightPiece createBlackPiece() {
        this.builder.setAllegiance(this.blackAllegiance);
        this.builder.setState(new NullState());
        this.builder.setHealth(3);
        this.builder.setArmor(1);
        this.builder.setDamage(3);
        this.builder.setCanMove(true);
        this.builder.setCanAttack(true);
        this.builder.setSymbol("♞");

        KnightPiece knight = this.builder.getResult();
        knight.changeState(new HealthyState(knight));

        return knight;
    };
}
