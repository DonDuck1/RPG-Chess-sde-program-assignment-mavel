package org.example.pieces;

import org.example.allegiances.Allegiance;
import org.example.board.Square;
import org.example.states.PieceState;

import java.io.Serializable;

public class KnightPiece implements Piece, Serializable {
    private Allegiance allegiance;

    private PieceState state;

    private double health;

    private int armor;

    private double damage;

    private String symbol;

    private Boolean canMove;

    private Boolean canAttack;

    @Override
    public void setAllegiance(Allegiance allegiance) {
        this.allegiance = allegiance;
    }

    @Override
    public Allegiance getAllegiance() {
        return this.allegiance;
    }

    @Override
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public PieceState getState() {
        return this.state;
    };

    @Override
    public double getHealth() {
        return this.health;
    };

    @Override
    public double getDamage() {
        return this.damage;
    };

    @Override
    public void changeState(PieceState state) {
        this.state = state;
    }

    @Override
    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public void setDamage(double damage) {
        this.damage = damage;
    }

    @Override
    public void setCanMove(Boolean canMove) {
        this.canMove = canMove;
    }

    @Override
    public void setCanAttack(Boolean canAttack) {
        this.canAttack = canAttack;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public void multiplyDamage(double multiplier) {
        this.damage *= multiplier;
    }

    @Override
    public void move(Square currentSquare, Square squareToMoveTo, Square[][] squares) {
        if (canMove) {
            this.allegiance.moveKnight(currentSquare, squareToMoveTo, squares);
        }
    }

    @Override
    public void attack(Square currentSquare, Square squareToAttack, Square[][] squares) {
        if (canAttack) {
            this.allegiance.attackWithKnight(currentSquare, squareToAttack, squares);
        }
    }

    @Override
    public void specialAction(Square currentSquare, Square squareToAttack, Square[][] squares) {
        if (canAttack) {
            this.allegiance.specialActionWithKnight(currentSquare, squareToAttack, squares);
        }
    }

    @Override
    public void takeDamage(double damage) {
        this.health -= damage;
    }

    @Override
    public void gainHealth(double health) {
        this.health += health;
    }
}
